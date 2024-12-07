# Usar la imagen base de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo .jar desde la carpeta target al contenedor
COPY target/autoflixx-0.0.1-SNAPSHOT.jar autoflixx.jar

# Exponer el puerto 8080 para que el contenedor pueda recibir solicitudes
EXPOSE 8080

# Comando para ejecutar la aplicación en el contenedor
ENTRYPOINT ["java", "-jar", "autoflixx.jar"]