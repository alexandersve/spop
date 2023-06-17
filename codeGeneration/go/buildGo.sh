#!/bin/bash

set -e

# Set the source and target directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
SOURCE_DIR=${SCRIPT_DIR}src
TARGET_DIR=${SCRIPT_DIR}target

# Build the Docker image using the Dockerfile
docker build -t my-go-app ${SCRIPT_DIR}

# Create the target directory if it doesn't already exist
mkdir -p ${TARGET_DIR}

# Run the Docker container and copy the executable files to the target directory
docker run --rm -v ${SOURCE_DIR}:/src -v ${TARGET_DIR}:/target my-go-app

echo "Build complete. Executable files can be found in: ${TARGET_DIR}"
