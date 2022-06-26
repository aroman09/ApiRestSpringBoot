FROM maven:3.8.1-jdk-11
ADD target/crudapibit-docker.jar crudapibit-docker.jar
ENTRYPOINT ["java","-jar","/crudapibit-docker.jar"]
