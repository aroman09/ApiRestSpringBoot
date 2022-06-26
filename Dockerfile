FROM maven:3.8.1-jdk-11
<<<<<<< HEAD
EXPOSE 8080
ADD target/crudapibit-docker.jar crudapibit-docker.jar
ENTRYPOINT ["java","-jar","/crudapibit-docker.jar"]
=======
ADD target/crudapibit-docker.jar crudapibit-docker.jar
ENTRYPOINT ["java","-jar","/crudapibit-docker.jar"]
>>>>>>> 63137b8bb0da83f2c550497f7294a9b2b6445fd7
