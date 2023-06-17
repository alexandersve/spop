#!/bin/bash

# Set the source and target directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
SOURCE_DIR=${SCRIPT_DIR}../codeGeneration/go/src/
TEMP_DIR=${SCRIPT_DIR}temp/

# Build the Docker image using the Dockerfile
mkdir -p ${TEMP_DIR}
cp -r ${SOURCE_DIR} ${TEMP_DIR}src
docker build -t my-test-creater ${SCRIPT_DIR}
rm -rf ${TEMP_DIR}
