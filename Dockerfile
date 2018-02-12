FROM openjdk:8-jdk-alpine
MAINTAINER Bhupendra Pandey <pandeybhupen01@gmail.com>
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


#ADD ip-address-management-impl/target/ip-address-management-impl-exec.jar /app/app.jar
# EXPOSE 5005
#ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/app.jar", "-Dspring.profiles.active=$SPRING_PROFILES_ACTIVE"]