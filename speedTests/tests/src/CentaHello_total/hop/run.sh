#!/bin/bash

# Set the relevant directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
IMPORT_DIR=${SCRIPT_DIR}
EXE_DIR=${SCRIPT_DIR}

cd ${EXE_DIR}

# Find all hop .jar files and include them when running Main.java
java -cp "$(find ${IMPORT_DIR}/hop -name '*.jar' -type f | xargs echo | tr ' ' ':')":. run.Main
