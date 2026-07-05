# ---------- Build Stage ----------
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Maven wrapper aur pom copy karo
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Maven wrapper executable banao
RUN chmod +x mvnw

# Dependencies download karo
RUN ./mvnw dependency:go-offline

# Project source copy karo
COPY src src

# Project build karo
RUN ./mvnw clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Built JAR copy karo
COPY --from=builder /app/target/*.jar app.jar

# Render PORT environment variable use karega
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
