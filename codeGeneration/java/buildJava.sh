#!/bin/bash

set -e

# Check if the script is called with exactly one argument
if [ $# -ne 1 ]; then
    echo "Error: Wrong number of arguments. Usage: $0 hop/kettle"
    exit 1
fi

# Check if the first argument is valid
if ! [ "$1" = "hop" ] && ! [ "$1" = "kettle" ]; then
    echo "Error: The argument must be either 'string1' or 'string2'"
    exit 1
fi

# Set the source and target directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
SOURCE_DIR=${SCRIPT_DIR}src/
TARGET_DIR=${SCRIPT_DIR}target/

# Extract hop java files
if [ ! -e "${SCRIPT_DIR}apache-hop-src.zip" ]; then
    bash ${SCRIPT_DIR}downloadHopRequirements.sh
fi
if [ ${1} = "hop" ]; then
    rm -rf ${SOURCE_DIR}/hop
    rm -rf ${SOURCE_DIR}/data-integration
    unzip apache-hop-src.zip -d ${SOURCE_DIR}
fi

# Extract kettle java files
if [ ! -e "${SCRIPT_DIR}pentaho-data-integration.zip" ]; then
    bash ${SCRIPT_DIR}downloadKettleRequirements.sh
fi
if [ ${1} = "kettle" ]; then
    rm -rf ${SOURCE_DIR}/hop
    rm -rf ${SOURCE_DIR}/data-integration
    unzip pentaho-data-integration.zip -d ${SOURCE_DIR}
fi

# Build the Docker image using the Dockerfile
docker build -t my-java-app ${SCRIPT_DIR}

# Remove target directory and create a new one
rm -rf ${TARGET_DIR}
mkdir -p ${TARGET_DIR}

# Run the Docker container and copy the executable files to the target directory
if [ ${1} = "hop" ]; then
    docker run --user "$(id -u):$(id -g)" --rm -v ${TARGET_DIR}:/target my-java-app
    cp -r ${SOURCE_DIR}hop/config/ ${TARGET_DIR}config/
fi
if [ ${1} = "kettle" ]; then
    docker run --user "$(id -u):$(id -g)" --rm -v ${TARGET_DIR}:/target my-java-app
fi


echo "Build complete. Executable files can be found in: ${TARGET_DIR}"

