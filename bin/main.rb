# This program provides a graphical user interface to follow files as they are changing. 
# This functionality is similar to the unix tail utility with the -f option (tail -f somefile.log)
# The GUI allows the following of multiple files and the ability to define highlight colors
# for given regular expression matches.

require 'java'
require 'pp'
require 'optparse'
require 'ui.jar'
require 'Preferences'
require 'PreferencesDialog'
require 'utils'
require 'logger'
require 'config'


# This class provides the ability to open a file and monitor it for changes.
# It is a subclass of a javax.swing.JInternalFrame containing a jTextPane. 
# The detected file changes are appended to the JTextPane.
class RFileFollower < com.bsi.filefollower.FileFollower
 
  # The class is initialized by specifying the file (path) to open and follow.
  #
  # Params:
  #
  # - file: The path to the file to be monitored.
  def initialize(args={})
    super()
    args.assert_keys(:file)
    @args = args
    @file = File.new(@args[:file], "r")
    
    add_internal_frame_listener(javax.swing.event.InternalFrameListener.impl{|m,v|
      case  m.to_s
      when "internalFrameClosed"
        @running = false
        @file.close
      end
    })

    getMntmSuspend.add_action_listener{|e|
      stop
    }

    getMntmResume.add_action_listener{|e|
      start
    }

  end

  # Begin polling the file for changes
  # If the file size has changed and is greater than what it was the previous cycle,
  # append to the jTextPane. If it is smaller, show a message to indicate the file was
  # truncated.
  #
  # Params:
  # - delay: The number of seconds to wait between polls.
  def start(delay=1)
    @running = true

    Thread.abort_on_exception = true
    @thread = Thread.new{
      @file.seek(@file.stat.size)
      while @running
        begin
          line = @file.readline

          color = getTextPane.get_background

          # Determine if line should be highlighted based on global keywords
          $prefs[:global_keywords].each{|keyword|
            color = keyword[1] if Regexp.new(keyword[0]) =~ line
          } if not $prefs[:global_keywords].nil?

          appendToPane(getTextPane, line, color)

        rescue EOFError

          if should_reopen?
            @file.reopen(@args[:file])
            @file.seek(@file.stat.size)
            appendToPane(getTextPane, "\n*** File appears to have been truncated. File reopened. ***\n", Java::java.awt.Color.yellow)
          else
            @file.seek(0,File::SEEK_CUR)
          end
          sleep delay
        rescue Errno::ENOENT
        rescue Errno::ESTALE
          @file.reopen(@args[:file])
          @file.seek(@file.stat.size)
          appendToPane(getTextPane, "\n*** estale: File appears to have been truncated. File reopened. ***\n", Java::java.awt.Color.yellow)
        rescue Exception => e
          puts e.backtrace
        end
      end
    }
  end

  # Stop polling the file.
  def stop
    @running = false
  end

  private

  # Determine if a file should be reopenned bacause it was recreated, the size became smaller, etc.
  def should_reopen?()
    stat = File.stat(@args[:file])
    return true if stat.ino != @file.stat.ino or stat.size < @file.tell or stat.dev != @file.stat.dev
  end

  # Append text to the jTextPane using the specified color.
  # Parameters:
  #
  # - textpane: The JTextPane into which to append the text
  # - msg: The text to append
  # - color: The collor to display the text.
  def appendToPane( textpane,  msg,  color)
    sc = Java::javax.swing.text.StyleContext.getDefaultStyleContext();
    aset = sc.addAttribute(Java::javax.swing.text.SimpleAttributeSet::EMPTY, Java::javax.swing.text.StyleConstants::Background, color)
    len = textpane.getDocument().getLength();
    textpane.setCaretPosition(len);
    textpane.setCharacterAttributes(aset, false);
    textpane.replaceSelection(msg);
  end

end


# This is the class that inherits the GUI and responds to the various events from the user.
class MainFrame < com.bsi.filefollower.MainFrame

  @about_box = nil
  @global_preferences_dialog = nil
  def initialize
    super()

    getMntmExit.addActionListener do |e|
      set_visible false
      dispose
      java.lang.System.exit(0)
    end

		getMntmOpen.addActionListener{|e|
      # We always store the last path the user visited as a convenience.
      
      chooser = javax.swing.JFileChooser.new($prefs[:last_path] || '')

      if chooser.showOpenDialog(nil) == javax.swing.JFileChooser::APPROVE_OPTION
			  if File.exists?(chooser.get_selected_file.to_s)
          # Update the last path as the user may have changed it.
          $prefs[:last_path] = File.dirname(chooser.get_selected_file.to_s)
          begin
            $prefs.store
          rescue Exception => e
            $settings[:global_logger].error("Failed to write prefs: #{e}")
          end
  
          #create new internal frame to follow file
  				begin
    				ff = RFileFollower.new :file => chooser.get_selected_file.to_s
    				ff.set_title chooser.get_selected_file.to_s
    				getDesktop.add ff
    				ff.set_visible true
    				ff.start
  				rescue Errno::EACCES
  				  javax.swing.JOptionPane.showMessageDialog(nil, "File could not be opened. Access denied.", "Error", javax.swing.JOptionPane::OK_OPTION)
  				end
		  else
  				  javax.swing.JOptionPane.showMessageDialog(nil, "File #{chooser.get_selected_file.to_s} does not exist.", "Error", javax.swing.JOptionPane::OK_OPTION)
			end
		 end

    }

    getMntmPreferences.add_action_listener{|e|
      if @global_preferences_dialog.nil?
        @global_preferences_dialog = PreferencesDialog.new
        getDesktop.add(@global_preferences_dialog)
      end

      if not @global_preferences_dialog.visible
        @global_preferences_dialog.set_visible(true)
      end

    }

    getMntmAbout.add_action_listener{|e|

      if @about_box.nil?
        @about_box = Java::com.bsi.filefollower.DialogAbout.new
        @about_box.getLabel_about.set_text($settings[:title] + " " + $settings[:version])
        getDesktop.add(@about_box)
      end

      if not @about_box.visible
        @about_box.set_visible(true)
      end

    }

    getMenu_item_License.add_action_listener{|e|

      if @licensebox.nil?
        @licensebox = Java::com.bsi.filefollower.DialogLicense.new
        getDesktop.add(@licensebox)
      end

      if not @licensebox.visible
        @licensebox.set_visible(true)
      end

    }

    getMntmCascade.add_action_listener{|e|
      do_cascade(getDesktop)
    }
   
    getMntmTile.add_action_listener{|e|
      do_tile(getDesktop.get_all_frames, getDesktop.get_bounds)
    }
    
  end

  def do_cascade(desktop, resize=true)
    bounds = desktop.get_bounds
    frames = desktop.get_all_frames
    separation = 25
    
    margin = frames.length*separation + separation
    width = bounds.width - margin
    height = bounds.height - margin
    
    frames.each_with_index{|frame,i|
      frame.setBounds( separation + bounds.x + i*separation,separation + bounds.y + i*separation, width, height )
    }

  end
  
  def do_tile( frames,  dBounds )
    cols = Integer(Math.sqrt(frames.length))
    rows = Integer(frames.length.ceil / cols)
    lastRow = frames.length - cols*(rows-1)

    if lastRow == 0
      rows--
      height = dBounds.height / rows
    else
      height = dBounds.height / rows
      if lastRow < cols
        rows--
        width = dBounds.width / lastRow

        (0..lastRow-1).each{|i|
          frames[cols*rows+i].setBounds( i*width, rows*height, width, height )
        }
      end
    end

    width = dBounds.width/cols
    (0..rows-1).each{|j|
      (0..cols-1).each{|i|
        frames[i+j*cols].setBounds( i*Integer(width), j*Integer(height),Integer(width), Integer(height) )
      }
    }

  end

  

end



optparse = OptionParser.new{|opt|

  opt.on('-p PREFS_FILE', "Path to the preferences file") do |arg|
    puts "Preferences file #{arg} does not exist" if not File.exists?(arg)
    $settings[:prefs_path] = arg
  end

}

optparse.parse!


# Load stored preferences
$prefs=Preferences.new(:filename => $settings[:prefs_path])
begin
  $prefs.load
rescue Exception => e
  error_msg=<<-EOF
An error occured while attempting to read the preferences file #{$settings[:prefs_path]}.

Although your prefrences will not be saved, you can still use the application. 

Would you like to continue?
EOF

  if javax.swing.JOptionPane.showConfirmDialog(nil, error_msg, "Error", javax.swing.JOptionPane::YES_NO_OPTION) == javax.swing.JOptionPane::NO_OPTION
    java.lang.System.exit(-1)
  end
end

# Attempt to set look and feel to Nimbus
begin

  javax.swing.UIManager.getInstalledLookAndFeels().each{|info|
    if /Nimbus/ =~ info.getName()
      javax.swing.UIManager.setLookAndFeel(info.getClassName())
      break
    end
  }
rescue Exception => e
end

#Create and show main window
main_ui = MainFrame.new
main_ui.set_title($settings[:title] + " " + $settings[:version])
main_ui.set_visible true

#Load files if specified on the command line
ARGV.each{|file|
  if File.file?(file)
    ff = RFileFollower.new :file => file.to_s
    ff.set_title file.to_s
    main_ui.getDesktop.add ff
    ff.set_visible true
    ff.start
  end
}

main_ui.do_cascade(main_ui.getDesktop)

# I need this when using Warbler for .jar apps. Warbler calls your script and exits at the end of the script.
# Here we join the main swing event thread to keep the script from exiting.
event_thread = nil
Java::javax.swing.SwingUtilities.invokeAndWait { event_thread = Java::java.lang.Thread.currentThread }
event_thread.join

