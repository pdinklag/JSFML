#!/bin/sh

# this script updates the install name of SFML binaries
#
# renames these files from working directory and edit some stuffs with install_name_tool : 
#  - libsfml-audio.2.0.dylib    => libsfml-audio.dylib
#  - libsfml-graphics.2.0.dylib => libsfml-graphics.dylib
#  - libsfml-system.2.0.dylib   => libsfml-system.dylib
#  - libsfml-window.2.0.dylib   => libsfml-window.dylib
#
# libsndfile and libfreetype are NOT handled by this script!
#


error() {
    echo "$@" 1>&2
    exit 1
}

audio="libsfml-audio.2.0.dylib"
graphics="libsfml-graphics.2.0.dylib"
system="libsfml-system.2.0.dylib"
window="libsfml-window.2.0.dylib"

if [ ! -f "$audio" ] || [ ! -f "$graphics" ] || [ ! -f "$system" ] || [ ! -f "$window" ] ;
then
    error "missing files"
fi


# rename files
n_audio="libsfml-audio.dylib"
n_graphics="libsfml-graphics.dylib"
n_system="libsfml-system.dylib"
n_window="libsfml-window.dylib"

mv "$audio" "$n_audio"
audio="$n_audio"

mv "$graphics" "$n_graphics"
graphics="$n_graphics"

mv "$system" "$n_system"
system="$n_system"

mv "$window" "$n_window"
window="$n_window"

# edit install name for each dylib
install_name_tool -id "$audio" \
                  -change @executable_path/../Frameworks/sndfile.framework/Versions/A/sndfile @loader_path/libsndfile.dylib \
                  -change @executable_path/../Frameworks/libsfml-system.2.dylib @loader_path/"$system" \
                  "$audio"

install_name_tool -id "$graphics" \
                  -change /usr/local/lib/libfreetype.6.dylib @loader_path/libfreetype.dylib \
                  -change /usr/X11/lib/libfreetype.6.dylib @loader_path/libfreetype.dylib \
                  -change @executable_path/../Frameworks/freetype.framework/Versions/A/freetype @loader_path/libfreetype.dylib \
                  -change @executable_path/../Frameworks/libsfml-window.2.dylib @loader_path/"$window" \
                  -change @executable_path/../Frameworks/libsfml-system.2.dylib @loader_path/"$system" \
                  "$graphics"

install_name_tool -id "$system" \
                  "$system"

install_name_tool -id "$window" \
                  -change @executable_path/../Frameworks/libsfml-system.2.dylib @loader_path/"$system" \
                  "$window"


