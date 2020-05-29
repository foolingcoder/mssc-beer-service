FROM openjdk:11-jdk-buster

MAINTAINER "Priteela"

EXPOSE 8080

WORKDIR  /usr/local/bin

COPY maven/mssc-beer-service-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java" , "-Dspring.profiles.active=localmysql", "-jar", "webapp.jar"]