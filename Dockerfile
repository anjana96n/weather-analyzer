FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/weather-analyzer-0.0.1-SNAPSHOT.jar weather-analyzer.jar
ENTRYPOINT ["java","-jar","/weather-analyzer.jar"]