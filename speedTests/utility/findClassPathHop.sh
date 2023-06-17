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
HOP_DIR=${SCRIPT_DIR}hop/

# Finding java class path
cd ${HOP_DIR}
JAR_PATH=$(grep -ri ${classname} * | grep -oE '\S+\.jar\b' | grep -vE "hop-ui" | grep -m1 "")
jar tf ${HOP_DIR}${JAR_PATH} | grep ${classname}.class


