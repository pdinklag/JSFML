#!/bin/sh
if [ `uname` = "Darwin" ]; then
    java -XstartOnFirstThread -jar jsfml-test.jar >jsfml-test.log 2>&1
else
    java -jar jsfml-test.jar >jsfml-test.log 2>&1
fi
