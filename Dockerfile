FROM openjdk:17
COPY build/libs/FileLoader-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 127.0.0.1:8080:8080 127.0.0.1:8081:8081 127.0.0.1:8082:8082 127.0.0.1:8083:8083 127.0.0.1:8084:8084
CMD ["java","-jar","app.jar"]