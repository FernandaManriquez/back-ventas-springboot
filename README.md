# Backend Ventas - Innovatech Chile

API REST encargada de gestionar la información relacionada con ventas y órdenes de compra del sistema Innovatech Chile.

Este backend forma parte de una solución compuesta por:

- Frontend React/Vite.
- Backend Ventas Spring Boot.
- Backend Despachos Spring Boot.
- Base de datos relacional MySQL.

## Tecnologías utilizadas

- Java
- Spring Boot
- Maven
- MySQL
- Docker
- AWS ECS Fargate
- Amazon ECR
- Amazon RDS
- Application Load Balancer
- GitHub Actions
- CloudWatch Logs

## Función dentro del sistema

El backend de ventas expone servicios relacionados con ventas y órdenes de compra.

En la arquitectura cloud, este servicio se ejecuta como una tarea independiente en Amazon ECS Fargate.  
El servicio se conecta a una base de datos MySQL alojada en Amazon RDS y es accesible mediante reglas de ruta configuradas en el Application Load Balancer.

## Puerto del servicio

Puerto local y del contenedor:

```text
8082
```

## Endpoints principales

Endpoint base local:

```text
http://localhost:8082/api/v1/ventas
```

Health check local:

```text
http://localhost:8082/api/v1/ventas/health
```

En AWS, el backend se expone mediante el Application Load Balancer usando la ruta:

```text
/api/v1/ventas
```

Health check en AWS:

```text
/api/v1/ventas/health
```

## Ejecución local con Maven

Entrar a la carpeta del proyecto Spring Boot:

```bash
cd Springboot-API-REST
```

Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

En Windows también se puede usar:

```bash
mvnw.cmd spring-boot:run
```

## Ejecución con Docker

Construir la imagen Docker:

```bash
docker build -t back-ventas-springboot ./Springboot-API-REST
```

Ejecutar el contenedor:

```bash
docker run -p 8082:8082 back-ventas-springboot
```

## Variables de entorno

Para conectarse a la base de datos se utilizan variables de entorno.  
Esto evita dejar credenciales directamente escritas en el código fuente.

Variables esperadas:

```text
SERVER_PORT=8082
DB_ENDPOINT=endpoint-rds
DB_PORT=3306
DB_NAME=innovatech
DB_USERNAME=usuario
DB_PASSWORD=password
```

## Base de datos

El backend se conecta a una base de datos MySQL.

En ambiente local puede utilizarse MySQL mediante Docker Compose.  
En ambiente cloud se utiliza Amazon RDS MySQL.

## Dockerfile

El Dockerfile permite empaquetar la aplicación Spring Boot como una imagen Docker para su despliegue en ECS Fargate.

El contenedor expone el puerto:

```text
8082
```

## CI/CD

El pipeline de GitHub Actions automatiza el proceso de despliegue.

Flujo esperado:

1. Checkout del repositorio.
2. Ejecución de pruebas con Maven.
3. Construcción de imagen Docker.
4. Publicación de la imagen en Amazon ECR.
5. Actualización del servicio correspondiente en Amazon ECS Fargate.

## Despliegue en AWS

Servicios utilizados:

- Amazon ECR para almacenar la imagen Docker.
- Amazon ECS Fargate para ejecutar el backend.
- Amazon RDS MySQL como base de datos.
- Application Load Balancer para enrutar solicitudes.
- CloudWatch Logs para observabilidad.
- GitHub Actions para CI/CD.

## Seguridad

Buenas prácticas consideradas:

- Uso de variables de entorno para credenciales.
- Uso de GitHub Secrets para credenciales AWS.
- Exposición mínima de puertos.
- Acceso a RDS restringido desde los servicios ECS.
- Uso de roles IAM con permisos necesarios.

## Evidencias esperadas para la defensa

Para la defensa técnica se deben mostrar:

- Dockerfile del backend.
- Imagen publicada en Amazon ECR.
- Servicio ECS en estado Running.
- Target Group asociado en estado Healthy.
- Endpoint `/api/v1/ventas/health` respondiendo correctamente.
- Logs del servicio en CloudWatch.
- Pipeline de GitHub Actions ejecutado correctamente.

## Integrantes

- Catrina Corral
- Fernanda Manríquez

## Asignatura

Introducción a Herramientas DevOps - ISY1101