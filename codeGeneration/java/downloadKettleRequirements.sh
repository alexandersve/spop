#!/bin/bash

# Set the relevant directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/

wget "https://privatefilesbucket-community-edition.s3.us-west-2.amazonaws.com/9.4.0.0-343/ce/client-tools/pdi-ce-9.4.0.0-343.zip" -O ${SCRIPT_DIR}pentaho-data-integration.zip
