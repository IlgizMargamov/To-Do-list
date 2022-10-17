FROM openjdk:17
MAINTAINER com.example
COPY target/todolist-0.0.1-SNAPSHOT.jar todolist-0.0.1.jar
ENTRYPOINT ["java","-jar","/todolist-0.0.1.jar"]