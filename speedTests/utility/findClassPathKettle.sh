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
KETTLE_DIR=${SCRIPT_DIR}data-integration/

# Finding java class path
cd ${KETTLE_DIR}
JAR_PATH=$(grep -ri ${classname} * | grep -oE '\S+\.jar\b' | grep -vE "kettle-ui")
jar tf ${KETTLE_DIR}${JAR_PATH} | grep ${classname}.class


