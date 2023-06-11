FROM openjdk:11-jre
WORKDIR /api-marketplace
COPY target/*.jar /api-marketplace/api-marketplace-0.0.1-SNAPSHOT.jar
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m -jar api-marketplace-0.0.1-SNAPSHOT.jar