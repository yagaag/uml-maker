#!/bin/bash

rm -r -f release/win
rm release/win.zip
mkdir release/win

cp -r $JAVA_HOME release/win/jre
java -jar lib/launch4j/launch4j.jar config.xml
zip -vr release/umlmaker_windows.zip release/win/