class Preferences < Hash
  
  def initialize(args={})
    super()
    @args = args
  end

  def store
      File.open(@args[:filename],'wb'){|f|
        f.write Marshal.dump(self.to_hash)
      } 
  end

  def load
      File.open(@args[:filename], 'rb'){|f|
        hash = Marshal.load(f)
        replace(hash.to_hash)
      } if File.exists?(@args[:filename])
  end

end
