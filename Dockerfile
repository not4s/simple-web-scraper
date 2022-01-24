FROM maven:3.5-jdk-11 as BUILD

RUN apt-get install maven && apt-get install pandoc

COPY . /usr/src/app
RUN mvn --batch-mode -f /usr/src/app/pom.xml clean package

FROM openjdk:11-jdk
ENV PORT 5000
EXPOSE 5000
COPY --from=BUILD /usr/src/app/target /opt/target
WORKDIR /opt/target

CMD ["/bin/bash", "-c", "find -type f -name '*-with-dependencies.jar' | xargs java -jar"]
