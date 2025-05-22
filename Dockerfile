FROM openjdk:17-oracle
ADD target/book-management-app.jar book-management-app.jar
ENTRYPOINT ["java", "-jar", "/book-management-app.jar"]