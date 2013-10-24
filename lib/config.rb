

$settings={
  :title => 'file-chaser',
  :version => '1.0',
  :global_logger => Logger.new(File.new("#{ENV['HOME']}/file-chaser.log","a")),
  :prefs_path => "#{ENV['HOME']}/file-chaser.prefs"
}  


