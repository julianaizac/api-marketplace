FROM openjdk:17-jdk
WORKDIR /api-marketplace
COPY target/*.jar /api-marketplace/api-marketplace-0.0.1-SNAPSHOT.jar
EXPOSE 8081
CMD java -XX:+UseContainerSupport -Xmx512m  -Dserver.port=8081 -jar api-marketplace-0.0.1-SNAPSHOT.jar