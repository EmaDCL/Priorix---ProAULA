# ==========================================
# ETAPA 1: Compilación (Build)
# ==========================================
# 1. Usamos una imagen oficial que contiene Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build

# 2. Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos los archivos de configuración de Maven para descargar las dependencias
COPY pom.xml .

# 4. Descargamos las dependencias necesarias (esto optimiza la caché de Docker)
RUN mvn dependency:go-offline -B

# 5. Copiamos el código fuente de nuestro proyecto
COPY src ./src

# 6. Compilamos el proyecto y generamos el archivo .jar (saltando los tests)
RUN mvn clean package -DskipTests

# ==========================================
# ETAPA 2: Ejecución (Runtime)
# ==========================================
# 7. Usamos una imagen limpia y compacta con el entorno de ejecución de Java 21 (JRE)
FROM eclipse-temurin:21-jre-jammy

# 8. Definimos el directorio de trabajo para la ejecución
WORKDIR /app

# 9. Copiamos el archivo .jar generado en la Etapa 1 hacia esta nueva imagen limpia
COPY --from=build /app/target/*.jar app.jar

# 10. Informamos el puerto en el que escuchará el contenedor (Spring Boot usa el 8080 por defecto)
EXPOSE 8080

# 11. Comando definitivo para arrancar la aplicación cuando el contenedor inicie
ENTRYPOINT ["java", "-jar", "app.jar"]

