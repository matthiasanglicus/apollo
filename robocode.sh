#!/bin/sh
pwd=`pwd`
cd "${0%/*}"
java -Xmx512M -cp "libs/robocode.jar:libs/comp7307.jar" robocode.Robocode $*
cd "${pwd}"
