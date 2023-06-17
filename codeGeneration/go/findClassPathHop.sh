#!/bin/bash

# Handling input flags
classname=""
while getopts "c:" opt; do
  case ${opt} in
    c ) classname=${OPTARG}
        ;;
    \? ) echo "Invalid option: -${OPTARG}" 1>&2
         exit 1
         ;;
    : ) echo "Option -${OPTARG} requires the classname to search for" 1>&2
         exit 1
         ;;
  esac
done

if [[ -z ${classname} ]]; then
  echo "Error: -c flag is required." 1>&2
  exit 1
fi

set -e


# Set the source directory
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
SOURCE_DIR=${SCRIPT_DIR}input/
HOP_DIR=${SOURCE_DIR}hop/

# Extract hop .jar files
newHop=false
if [ ! -e "${SCRIPT_DIR}apache-hop-2.2.0-src.zip" ]; then
    bash ${SCRIPT_DIR}downloadHopRequirements.sh
    newHop=true
fi
if [ ${newHop} = "true" ] || ! [ -d ${SOURCE_DIR}hop ] || ! [ "$(ls -A ${SOURCE_DIR}hop)" ]; then
    rm -rf ${SOURCE_DIR}hop
    unzip ${SCRIPT_DIR}apache-hop-2.2.0-src.zip -d ${SOURCE_DIR}
fi

# Finding java class path
cd ${HOP_DIR}
JAR_PATH=$(grep -ri ${classname} * | grep -oE '\S+\.jar\b' | grep -vE "hop-ui")
jar tf ${HOP_DIR}${JAR_PATH} | grep ${classname}.class


