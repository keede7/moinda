FROM openjdk:17-jdk

ARG ROOT_API_DIRECTORY=boilerplate-api
ARG JAR_FILE_NAME=${ROOT_API_DIRECTORY}-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=${ROOT_API_DIRECTORY}/build/libs/${JAR_FILE_NAME}
ADD ${JAR_FILE} ${ROOT_API_DIRECTORY}.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/${ROOT_API_DIRECTORY}.jar"]