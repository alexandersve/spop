#!/bin/bash

# Check correct amount of arguments
if [ $# -ne 1 ]; then
    echo "Error: Wrong number of arguments. Usage: $0 sourceType"
    exit 1
fi


# Set relevant directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
SOURCE_DIR=${SCRIPT_DIR}target/
TARGET_DIR=/home/alexanders/exjobb/codeGeneration/java/src/

cd ${SOURCE_DIR}

# Run executable in target directory
if [ "$1" = "hop" ]; then
    rm ${TARGET_DIR}/*.java
    ./spop -t "$1" -i "../input/Hello.hwf" -o ${TARGET_DIR}
elif [ "$1" = "kettle" ]; then
    rm ${TARGET_DIR}/*.java
    ./spop -t "$1" -i "../input/Hello.kjb" -o ${TARGET_DIR}
else
    echo "Error: Invalid argument. The first argument must be equal to hop or kettle."
    exit 1
fi
