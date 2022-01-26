FROM maven:3.8.4-jdk-11

USER root
RUN apt-get update
RUN apt-get install -y texlive-xetex
RUN apt-get install -y pandoc

COPY . .

RUN mvn package

CMD sh target/bin/simplewebapp

RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
