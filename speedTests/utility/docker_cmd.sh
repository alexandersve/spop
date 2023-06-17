#!/bin/bash

RUN_TYPE=$1
INPUT_FILE=$2

if [ ! -e "/src" ] && [ -z "${INPUT_FILE}" ]; then
    echo "Error: Either java source code must have been shared to /src or an inputfile must have been shared and specified"
    exit 1
fi

if [ ${RUN_TYPE} = "kettle" ]; then
    cp -r /downloads/data-integration /input
    cp -r /downloads/data-integration /target
    cp /utility/runKettle.sh /target/run.sh
elif [ ${RUN_TYPE} = "hop" ]; then
    cp -r /downloads/hop /input
    cp -r /downloads/hop /target
    cp -r /downloads/hop/config /target
    cp /utility/runHop.sh /target/run.sh
else 
    echo "Error: Invalid argument. The first argument must be equal to hop or kettle."
    exit 1
fi

mkdir /target/input
if ! [ -z ${INPUT_FILE} ]; then
    cp -r /generator /input
    cp /utility/findClassPath* /input
    /input/generator/spop -t ${RUN_TYPE} -i ${INPUT_FILE} -o /input/src

    cp ${INPUT_FILE} /target/input/
else
    mv /src/*.java /input/src/

    cp -r /src/* /target/input/
fi 


cd /input

javac -cp "$(find . -name '*.jar' -type f | xargs echo | tr ' ' ':')" -d /target src/*.java && chmod 775 -R /target

mkdir /target/javaSRC
cp -r /input/src/*.java /target/javaSRC 
