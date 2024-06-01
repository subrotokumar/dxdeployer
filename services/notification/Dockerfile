# FROM docker.io/openjdk:21-jdk-slim as builder
# WORKDIR /app
# COPY . /app/
# RUN ./mvnw clean package
# 
# FROM docker.io/openjdk:21-jdk-slim
# WORKDIR /app
# COPY --from=builder /app/target/*.jar /app/app.jar
# COPY ./target/*.jar /app/app.jar
# RUN chmod +x /app/app.jar
# EXPOSE 1111
# CMD ["java", "-jar", "app.jar"]

FROM docker.io/eclipse-temurin:21-jdk-alpine
COPY target/notification.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
