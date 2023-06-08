FROM openjdk:17-jdk

ARG JAR_NAME=moinda-api
ARG JAR_FILE_MAIN_NAME=moinda
ARG JAR_FILE_NAME=${JAR_NAME}-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=/${JAR_NAME}/build/libs/${JAR_FILE_NAME}
ADD ${JAR_FILE} /${JAR_FILE_MAIN_NAME}.jar

ENTRYPOINT ["java", "-jar", "/moinda.jar"]