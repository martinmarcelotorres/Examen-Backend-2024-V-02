FROM alpine:latest

# Instala las dependencias necesarias para tu aplicación
RUN apk update && apk add --no-cache openjdk17
# Crea un directorio para tu aplicación
WORKDIR /app
# Copia los archivos necesarios para tu aplicación
COPY target/*jar order-managment-0.0.1-SNAPSHOT.jar
# Expone el puerto que utiliza tu aplicación
EXPOSE 8080
# Define el comando que se ejecutará cuando inicies un contenedor de esta imagen
CMD ["java", "-jar", "order-managment-0.0.1-SNAPSHOT"]