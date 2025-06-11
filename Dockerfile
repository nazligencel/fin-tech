# Build Aşaması
# Kullandığınız base imaj (örneğin Temurin 17 ve Maven içeren bir imaj)
FROM maven:3.9.6-eclipse-temurin-17 AS build
# VEYA FROM eclipse-temurin:17-jdk-jammy AS build (ve Maven'ı manuel kuruyorsanız)

WORKDIR /app  # Çalışma dizinini /app olarak ayarla

# 1. ADIM: Önce mvnw ve pom.xml gibi build için gerekli temel dosyaları kopyala
COPY mvnw ./mvnw         # mvnw dosyasını /app/mvnw olarak kopyala
COPY .mvn ./.mvn         # .mvn klasörünü /app/.mvn olarak kopyala
COPY pom.xml ./pom.xml   # pom.xml dosyasını /app/pom.xml olarak kopyala

# 2. ADIM: Kopyalanan mvnw dosyasına yürütme izni ver
RUN chmod +x ./mvnw       # Şimdi ./mvnw /app dizininde olduğu için bulunabilir

# 3. ADIM: Bağımlılıkları indir (Docker katman önbelleğinden faydalanmak için kaynak koddan önce)
RUN ./mvnw dependency:go-offline -B # VEYA ./mvnw dependency:resolve

# 4. ADIM: Kaynak kodunu kopyala
COPY src ./src           # src klasörünü /app/src olarak kopyala

# 5. ADIM: Uygulamayı paketle
RUN ./mvnw package -DskipTests

# Çalıştırma Aşaması
FROM eclipse-temurin:17-jre-jammy # VEYA kullandığınız JRE/JDK imajı
WORKDIR /app
EXPOSE 8080 # Uygulamanızın dinlediği port (Spring Boot varsayılanı)
COPY --from=build /app/target/*.jar app.jar # Build aşamasından JAR'ı kopyala
ENTRYPOINT ["java", "-jar", "app.jar"]