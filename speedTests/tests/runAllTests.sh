#!/bin/bash

echo "Start: $(date +'%Y-%m-%d %H:%M:%S')"

# Set relevant directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
RUN_DIR=${SCRIPT_DIR}scripts

# Set total number of runs in tests
n=101

# Change to the directory containing the scripts
cd ${RUN_DIR}

# Loop through all .sh files in the directory
total_scripts=$(find . -maxdepth 1 -type f -name "*.sh" | wc -l)
m=0
for script in *.sh; do
    # Tell script number
    ((m++))
    echo -ne "Starting Speed Test ${m}/${total_scripts}\r"

    # Check if the file is a regular file
    if [ -f "$script" ]; then
        # Run the script
        bash "$script" $n
    fi
done

echo "Stop: $(date +'%Y-%m-%d %H:%M:%S')"
