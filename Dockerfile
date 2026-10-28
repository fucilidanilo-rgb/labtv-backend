
# FASE 1 - Compilazione Spring Boot con Java 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package

# FASE 2 - Esecuzione con Java 21
FROM eclipse-temurin:21-jre

WORKDIR /app

# Importa il certificato CA pubblico di Aiven
COPY aiven-ca.pem /app/aiven-ca.pem

RUN keytool -importcert \
    -noprompt \
    -trustcacerts \
    -alias aiven-mysql \
    -file /app/aiven-ca.pem \
    -cacerts \
    -storepass changeit

# Copia il backend compilato
COPY --from=build /app/target/*.jar app.jar

ENV PORT=10000

EXPOSE 10000

# Avvia Spring Boot con memoria limitata
CMD ["sh", "-c", "exec java -Xms64m -Xmx256m -Dserver.port=${PORT} -jar app.jar"]
