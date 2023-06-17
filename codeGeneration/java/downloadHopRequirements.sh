#!/bin/bash

# Set the relevant directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/

wget "https://www.apache.org/dyn/closer.cgi?filename=hop/2.3.0/apache-hop-client-2.3.0.zip&action=download" -O ${SCRIPT_DIR}apache-hop-src.zip
