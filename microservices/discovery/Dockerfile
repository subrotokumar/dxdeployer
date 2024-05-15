FROM docker.io/openjdk:21-jdk-slim as builder
# WORKDIR /app
# COPY . /app/
# RUN ./mvnw clean package

FROM docker.io/openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
COPY ./target/*.jar /app/app.jar
RUN chmod +x /app/app.jar
EXPOSE 8761
CMD ["java", "-jar", "app.jar"]
