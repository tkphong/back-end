FROM openjdk:17
FROM maven:latest
WORKDIR /app
#copy from your Host(PC, laptop) to container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#Run this inside the image
RUN mvn dependency:go-offline
COPY src ./src
#run inside container
CMD ["mvn", "spring-boot:run"]