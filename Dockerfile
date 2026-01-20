FROM eclipse-temurin:25

LABEL maintainer="sushilgiacr@gmail.com"

WORKDIR /app

COPY target/emp-dept-management-0.0.1-SNAPSHOT.jar /app/emp-dept-management.jar

ENTRYPOINT ["java", "-jar", "emp-dept-management.jar"]