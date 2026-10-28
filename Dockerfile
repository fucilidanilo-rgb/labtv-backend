
# Fase 1: compilazione con Maven e Java 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package

# Fase 2: avvio con Java 21
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENV PORT=10000

EXPOSE 10000

CMD ["sh", "-c", "exec java -Xms64m -Xmx256m -Dserver.port=${PORT} -jar app.jar"]
