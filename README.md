# Api Rest de un Crud usando Spring Boot

Proyecto que permite realizar un CRUD usando Spring Boot con la base de datos relacional en memoria H2; ademas de desplegar el aplicativo en un contenedor docker.

## Instalación

Desde el proyecto, abra su terminal y use [maven] para instalar el aplicativo.

```bash
mvn clean install
```

## Despliegue docker

```docker

#procesa archivo docker file para la nueva imagen del aplicativo
docker build -t crudapibit-docker.jar .

#Inicia el contenedor
docker run -it -p 8080:8080 crudapibit-docker.jar

```

## Licencia
[MIT](https://choosealicense.com/licenses/mit/)
