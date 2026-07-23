FROM maven:3.9.14-eclipse-temurin-25 AS build
WORKDIR /app
COPY . . 
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar product.jar
ENTRYPOINT [ "java","-jar","product.jar" ]
