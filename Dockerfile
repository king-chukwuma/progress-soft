FROM openjdk:18.0.2.1-jdk
WORKDIR /app
ADD . .
CMD ["./mvnw", "clean", "install"]
COPY /target/progress-soft-assessment.jar /app
EXPOSE 8090:8090
ENTRYPOINT ["java", "-jar", "progress-soft-assessment.jar"]
