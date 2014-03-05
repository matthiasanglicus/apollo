#!/bin/sh
pwd=`pwd`
cd "${0%/*}"
java -Xdock:icon=robocode.ico -Xdock:name=Robocode -Xmx512M -cp "libs/robocode.jar:libs/comp7307.jar" robocode.Robocode $*
cd "${pwd}"
