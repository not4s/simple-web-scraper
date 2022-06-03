FROM maven:3.8.4-jdk-11

RUN apt-get update
RUN apt-get install -y texlive-xetex
RUN apt-get install -y pandoc

RUN apt-get install -y python3-pip

RUN pip install bs4
RUN pip install urllib3

COPY . .

RUN mvn package

CMD sh target/bin/simplewebapp