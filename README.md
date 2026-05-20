Backend Ventas

Microservicio backend desarrollado con Spring Boot para la gestión de ventas y órdenes de compra.
El proyecto fue contenedorizado utilizando Docker, publicado en Docker Hub y desplegado en una instancia EC2 de AWS como parte de la Evaluación Parcial N°2 de Introducción a Herramientas DevOps.

Tecnologías utilizadas
Java 17
Spring Boot
Maven
Docker
Docker Compose
Docker Hub
GitHub Actions
AWS EC2
MySQL Connector
Repositorio
https://github.com/FernandaManriquez/back-ventas-springboot
Imagen en Docker Hub
ninii04/back-ventas-springboot:latest
Puerto utilizado
8082
Descripción del proyecto

Este microservicio backend permite gestionar órdenes de venta y compras del sistema principal.

El backend fue desarrollado utilizando Spring Boot y posteriormente contenedorizado con Docker para su despliegue en AWS EC2.

El servicio fue desplegado junto al microservicio de despachos utilizando Docker Compose.

Arquitectura general
Frontend React + Vite
        |
        v
Backend Ventas (Spring Boot)
Puerto 8082
        |
        v
Backend Despachos (Spring Boot)
Puerto 8081
Ejecución local

Compilar proyecto:

mvn clean package

Ejecutar aplicación:

java -jar target/*.jar
Construcción de imagen Docker
docker build -t back-ventas-springboot .
Ejecución con Docker
docker run -p 8082:8082 back-ventas-springboot
Docker Compose utilizado en EC2

Archivo docker-compose.yml utilizado para desplegar los microservicios backend:

services:
  despachos:
    image: ninii04/back-despachos-springboot:latest
    container_name: back-despachos
    restart: always
    ports:
      - "8081:8081"

  ventas:
    image: ninii04/back-ventas-springboot:latest
    container_name: back-ventas
    restart: always
    ports:
      - "8082:8082"
Instalación de Docker en EC2
sudo dnf update -y
sudo dnf install docker -y
sudo systemctl start docker
sudo systemctl enable docker
Instalación de Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

docker-compose --version
Creación de carpeta de despliegue
mkdir -p app
cd app
Levantamiento de contenedores
sudo docker-compose up -d

Verificación de contenedores:

sudo docker ps
Actualización de contenedores
sudo docker-compose pull

sudo docker-compose down

sudo docker-compose up -d
Endpoint principal
http://3.238.144.162:8082

Ejemplo de endpoint:

http://3.238.144.162:8082/api/v1/ventas
CI/CD con GitHub Actions

El proyecto utiliza GitHub Actions para automatizar la construcción y publicación de imágenes Docker.

El workflow se ejecuta automáticamente al realizar push sobre la rama:

deploy
Pipeline implementado

El pipeline realiza automáticamente:

Checkout del repositorio.
Login en Docker Hub.
Build de la imagen Docker.
Push de la imagen actualizada hacia Docker Hub.
Secrets utilizados
DOCKER_HUB_USERNAME
DOCKER_HUB_TOKEN

Estos secrets fueron configurados dentro de GitHub Actions para autenticar Docker Hub de forma segura.

Configuración de variables de entorno

El backend fue preparado para utilizar variables de entorno relacionadas con base de datos:

DB_ENDPOINT
DB_PORT
DB_NAME
DB_USERNAME
DB_PASSWORD
Persistencia y base de datos

Durante el desarrollo se realizaron pruebas de integración utilizando MySQL y volúmenes Docker para persistencia de datos.

La configuración fue validada mediante Docker Compose y variables de entorno.

Debido a las limitaciones de recursos de la instancia t3.micro proporcionada por AWS Academy, la integración simultánea de MySQL y múltiples microservicios Spring Boot generó problemas de estabilidad y saturación de recursos durante la demostración.

Por motivos de estabilidad del despliegue, se mantuvo la arquitectura basada en microservicios contenedorizados y se documentó la persistencia como mejora técnica futura.

Evidencias de funcionamiento

Se verificó correctamente:

Imagen publicada en Docker Hub.
Pipeline ejecutado exitosamente en GitHub Actions.
Contenedores desplegados en AWS EC2.
Docker Compose funcionando correctamente.
Comunicación entre frontend y backend mediante IP pública.
Consideraciones

El proyecto fue desplegado utilizando una arquitectura basada en contenedores Docker y microservicios independientes.

La separación entre frontend y backend permitió demostrar conceptos de:

Contenedorización.
Despliegue en AWS EC2.
Docker Compose.
Automatización CI/CD.
Publicación de imágenes en Docker Hub.
Comunicación entre servicios distribuidos.
