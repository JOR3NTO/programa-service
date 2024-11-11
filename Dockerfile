
FROM openjdk:17-jdk-slim
LABEL authors="team4"
# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /programa-service

# Copiar el archivo JAR generado al contenedor
COPY target/curso-service-0.0.1-SNAPSHOT.jar programa-service.jar

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]