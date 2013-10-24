
How to build file-chaser
========================


file-chaser comprises two parts: Java code for the user interface and Ruby code for the logic.
Building file-chaser from scratch requires the following from the root of the Git clone of the
repository:

1. Build the Java classes
  1. javac -d build/ src/com/bsi/filefollower/*
1. Create a jar file for the user interface:
	1. jar -cf lib/ui.jar -C build .


Launching file-chaser
=====================

jruby -I lib bin/main.rb


