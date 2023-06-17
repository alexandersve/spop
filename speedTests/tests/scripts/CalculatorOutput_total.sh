#!/bin/bash

set -e

# Set name
name=CalculatorOutput_total

# Set start time
TIME=$(date +"%y-%m-%d_%H-%M")

# Set the source and target directories
SCRIPT_DIR=$(dirname -- "$(readlink -f -- "$BASH_SOURCE")")/
SOURCE_DIR=${SCRIPT_DIR}../src/${name}/
TARGET_DIR=${SCRIPT_DIR}../results/${name}_${TIME}/
LOCAL_TARGET_DIR=${SOURCE_DIR}results/test_${TIME}/
GEN_KETTLE_DIR=${SOURCE_DIR}kettle/
GEN_HOP_DIR=${SOURCE_DIR}hop/
KETTLE_DIR=${SOURCE_DIR}kettle/data-integration/
HOP_DIR=${SOURCE_DIR}hop/hop/

# Create target directories if they doesn't exist
mkdir -p ${TARGET_DIR}
mkdir -p ${LOCAL_TARGET_DIR}

# Start speed test
echo -e "Starting Speed Test"

TIMEFORMAT="%R %U %S"
touch ${GEN_KETTLE_DIR}calc.txt
touch ${GEN_HOP_DIR}calc.txt

# Check if custom nr of runs are set
if [ -z "$1" ]; then
    n=101
else
    n=$1
fi

for (( i=1; i<=$n; i++ ))
do
    echo -ne "\tRun ${i}/${n} \r"
    (time $(bash ${KETTLE_DIR}pan.sh -file=${KETTLE_DIR}../input/CalculatorOutput.ktr &> /dev/null)) &>> ${LOCAL_TARGET_DIR}kettle.log
    (time $(bash ${HOP_DIR}hop-run.sh -j default -r local -f ${SOURCE_DIR}hop/input/CalculatorOutput.hpl &> /dev/null)) &>> ${LOCAL_TARGET_DIR}hop.log
done

rm ${GEN_KETTLE_DIR}calc.txt
rm ${GEN_HOP_DIR}calc.txt

echo -e "\e[1AFinished Speed Test"

# Extract summary
cd ${LOCAL_TARGET_DIR}
for log in *.log; do
    # Check if the file is a regular file
    if [ -f "$log" ]; then
        # Extract log info
        awk -f ${SCRIPT_DIR}createSummary.awk $log $log > ${TARGET_DIR}${log}
    fi
done
