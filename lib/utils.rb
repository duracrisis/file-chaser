

class Hash
  def assert_keys(*keys)
    keys.each{|key|
      raise Exception.new("missing key #{key}") if not self.has_key?(key)
    }
  end
end

if $0 == __FILE__
  
  my_hash={:a => 'a', :b => 'b'}
  
  puts my_hash.assert_keys(:a)  
  puts my_hash.assert_keys(:a, :b)
  puts my_hash.assert_keys(:a, :b, :c)
  
end