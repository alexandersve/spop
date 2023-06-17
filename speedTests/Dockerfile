# Stage 1: Build the Go program
FROM golang:1.20.4-alpine3.17 AS go_builder

WORKDIR /spop
COPY temp/src .

RUN go mod init spop && mkdir /target

RUN go build -o /target/spop

# Stage 2: Download and unpack required files
FROM alpine:3.17 AS downloader

WORKDIR /download

RUN apk add --no-cache wget unzip

RUN wget "https://www.apache.org/dyn/closer.cgi?filename=hop/2.3.0/apache-hop-client-2.3.0.zip&action=download" -O apache-hop-2.2.0-src.zip
RUN unzip apache-hop-2.2.0-src.zip -d /input

RUN wget "https://privatefilesbucket-community-edition.s3.us-west-2.amazonaws.com/9.4.0.0-343/ce/client-tools/pdi-ce-9.4.0.0-343.zip" -O pentaho-data-integration.zip
RUN unzip pentaho-data-integration.zip -d /input


# Stage 3: Build and run Java application
FROM eclipse-temurin:11.0.18_10-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /generator

# Copy Go program binary from the first stage
COPY --from=go_builder /target/spop /generator/

# Copy downloaded files from the second stage
COPY --from=downloader /input /downloads

# Copy bash utility scripts
WORKDIR /utility
COPY utility/ .
RUN chmod 777 -R .

# Create directory to handle inputs in
RUN mkdir /input && mkdir /input/src
RUN chmod 777 -R /input

# Create directory for compiled class files
RUN mkdir /target

# Compile the Java code, and run the Go program if argument is provided
CMD /utility/docker_cmd.sh ${RUN_TYPE} ${INPUT_FILE} 
