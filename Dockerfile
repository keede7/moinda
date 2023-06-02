FROM openjdk:17-jdk

ARG JAR_FILE_MAIN_NAME=moinda
ARG JAR_FILE_NAME=moinda-kotlin-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=moinda-api/build/libs/${JAR_FILE_NAME}
ADD ${JAR_FILE} ${JAR_FILE_MAIN_NAME}.jar

ENTRYPOINT ["java", "-jar", "/moinda.jar"]