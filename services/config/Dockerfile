# FROM docker.io/eclipse-temurin:21-jdk-alpine as builder
# WORKDIR /app
# COPY . /app/
# RUN ls
# RUN ./mvnw clean package

# FROM docker.io/eclipse-temurin:21-jdk-alpine
# WORKDIR /app
# COPY --from=builder /app/target/*.jar /app/app.jar
# RUN chmod +x /app/app.jar
# CMD ["java", "-jar", "app.jar"]

FROM docker.io/eclipse-temurin:21-jdk-alpine
COPY target/config.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]