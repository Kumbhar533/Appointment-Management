FROM maven:3.9.5 AS build
WORKDIR /app
ARG CONTAINER_PORT
COPY pom.xml /app 
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM  openjdk:17
COPY  --from=build /app/target/appointment.jar appointment.jar
EXPOSE ${CONTAINER_PORT}
CMD [ "java","-jar","appointment.jar" ]