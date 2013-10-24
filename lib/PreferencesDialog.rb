


class ColorEditor < Java::javax.swing.AbstractCellEditor
  include Java::javax.swing.table.TableCellEditor
  include Java::java.awt.event.ActionListener
  
    

    def initialize
      super
      
      @EDIT = "edit"  
      @button = Java::javax.swing.JButton.new;
      @button.setActionCommand(@EDIT)
      @button.addActionListener(self)
      @button.setBorderPainted(false)
      @currentColor = @button.get_background

      #Set up the dialog that the button brings up.
      @colorChooser = Java::javax.swing.JColorChooser.new;
      @dialog = Java::javax.swing.JColorChooser.createDialog(@button,
                                        "Please choose a Color",
                                        true,  #modal
                                        @colorChooser,
                                        self, #OK button handler
                                        nil) #no CANCEL button handler
    end

    def actionPerformed(e)
        if (@EDIT == (e.getActionCommand())) 
            #The user has clicked the cell, so
            #bring up the dialog.
            @button.setBackground(@currentColor);
            @colorChooser.setColor(@currentColor);
            @dialog.setVisible(true)

            fireEditingStopped() #Make the renderer reappear.

        else #User pressed dialog's "OK" button.
            @currentColor = @colorChooser.getColor
            @button.setBackground(@currentColor)
        end
    end

    #Implement the one CellEditor method that AbstractCellEditor doesn't.
     def getCellEditorValue() 
        @currentColor
     end

    #Implement the one method defined by TableCellEditor.
    def getTableCellEditorComponent(table, value, isSelected, row, column) 
        @currentColor = value
        @button
    end
end

class ColorRenderer < Java::javax.swing.JLabel
  include Java::javax.swing.table.TableCellRenderer

  def initialize
    super
    @unselectedBorder = nil
    @selectedBorder = nil
    @isBordered = true
    setOpaque(true) #MUST do this for background to show up.
  end

  def getTableCellRendererComponent(table, color, isSelected, hasFocus, row,  column)
    
    newColor = color
    setBackground(newColor)
    
   if (@isBordered)
     if (isSelected)
       if (@selectedBorder.nil?)
         @selectedBorder = Java::javax.swing.BorderFactory.createMatteBorder(2,5,2,5,table.getSelectionBackground())
       end
       setBorder(@selectedBorder)
     else
       if (@unselectedBorder.nil?)
         @unselectedBorder = Java::javax.swing.BorderFactory.createMatteBorder(2,5,2,5,table.getBackground())
       end
       setBorder(@unselectedBorder)
     end
   
  end

    self
  end
end



class PreferencesDialog < com.bsi.filefollower.JInternalFrame_Preferences
  
  
  def initialize
    super()
    
    #setup renderer and editor for the color column
    getTable_keywords.get_column_model.get_column(1).set_cell_editor(ColorEditor.new)
    getTable_keywords.get_column_model.get_column(1).set_cell_renderer(ColorRenderer.new)
    
    $prefs[:global_keywords].each{|keyword|
      getTable_keywords.get_model.add_row(keyword)
    } if not $prefs[:global_keywords].nil?
    
    getButton_keyword_add.add_action_listener{|e|
      getTable_keywords.get_model.add_row(['',getTable_keywords.get_background].to_java)
    }
    
    getTable_keywords.get_model.add_table_model_listener(Java::javax.swing.event.TableModelListener.impl{|method, e|
      
      if e.get_type == Java::javax.swing.event.TableModelEvent::UPDATE
        $prefs[:global_keywords] = getTable_keywords.get_model.get_data_vector
        begin
          $prefs.store
        rescue Exception => e
          $settings[:global_logger].error("Failed to write prefs: ${e}")
        end
      end

    })
    
    getButton_RemoveSelected.add_action_listener{|e|
      getTable_keywords.get_selected_rows.sort.reverse.each{|idx|
        getTable_keywords.get_model.removeRow(getTable_keywords.convert_row_index_to_model(idx))
      }
      $prefs[:global_keywords] = getTable_keywords.get_model.get_data_vector
      begin
        $prefs.store
      rescue Exception => e
        $settings[:global_logger].error("Faled to write prefs: ${e}")
      end
    }
    
    getButton_Close.add_action_listener{|e|
      set_visible(false)
    }
    
    
  end
  
end
