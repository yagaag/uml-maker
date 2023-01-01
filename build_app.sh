#!/bin/bash

ant bundle-UMLMaker
cp -f lib/JavaAppLauncher release/app/UMLMaker.app/Contents/MacOS/JavaAppLauncher
rm -r release/dmg
mkdir release/dmg
cat LICENSE > release/app/LICENSE.txt
npm list -g create-dmg || npm install --global create-dmg
create-dmg 'release/app/UMLMaker.app' release/dmg --dmg-title='UMLMaker_Installer'
