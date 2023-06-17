FROM golang:1.20.1-bullseye as builder

WORKDIR /spop

RUN go mod init spop && mkdir /src && mkdir /target

RUN go install golang.org/x/tools/cmd/goimports@latest

CMD goimports -w /src && cp -r /src/* /spop && go build -o /target/spop
