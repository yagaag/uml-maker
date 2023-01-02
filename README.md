## UML Maker

A customizable macOS UML Design tool with Java source code generation

### Usage

Download the installer from [this link](https://drive.google.com/file/d/1rYkz-mwUHOYxSWxlbyORrKCc59FW5aA1/view?usp=sharing) and make and share simple UML diagrams.
If macOS does not permit opening the app<sup>[[1]](#1)</sup>, go to System Preferences > Security and Privacy > General pane > Click on the lock on the left bottom to make changes and allow UMLMaker.
Alternatively, you can follow steps 2-6 in the Custom Build section to build the app and installer on your own system, and you're all set :)

### Demo

![demo](assets/demo.gif)


### Custom Build

1. Customize the Java source code to accommodate your requirements
2. Compile the source and build the jar
3. Change the directory of the jar in the build.xml file
4. Make sure to set the directory of your java installation as JAVA_HOME in your environment
5. Run the script build_app.sh to build the app as well as installer
6. You can find the installer in release/app/dmg. Use the installer to install your own custom UML Design Tool

#### Note
<a id="1">[1]</a> We're sorry for the inconvenience. As broke college students, we couldn't afford an Apple Developer account to sign the app. 