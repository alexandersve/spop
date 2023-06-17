#!/bin/bash

# Set the relevant directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
IMPORT_DIR=${SCRIPT_DIR}src
EXE_DIR=${SCRIPT_DIR}target

cd ${EXE_DIR}


# Run Main.java
if [ "$1" = "hop" ]; then

    # Find all hop .jar files and include them when running Main.java
    java -cp "$(find ${IMPORT_DIR}/$1 -name '*.jar' -type f | xargs echo | tr ' ' ':')":. run.Main

elif [ "$1" = "kettle" ]; then

    # Find all hop .jar files and include them when running Main.java
    java -cp "$(find ${IMPORT_DIR}/data-integration -name '*.jar' -type f | xargs echo | tr ' ' ':')":. run.Main

else
    echo "Error: Invalid argument. The first argument must be equal to hop or kettle."
    exit 1
fi
