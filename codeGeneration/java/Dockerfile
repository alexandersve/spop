FROM eclipse-temurin:11.0.18_10-jdk-alpine AS builder

WORKDIR /app

COPY src .

RUN mkdir /target

CMD javac -cp "$(find . -name '*.jar' -type f | xargs echo | tr ' ' ':')" -d /target *.java && chmod 775 -R /target
