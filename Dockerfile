# Build Aşaması
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml ./pom.xml
# Bağımlılıkları indir
RUN mvn dependency:go-offline -B # VEYA mvn dependency:resolve

COPY src ./src
# Uygulamayı paketle
RUN mvn package -DskipTests

# Çalıştırma Aşaması (Aynı)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]