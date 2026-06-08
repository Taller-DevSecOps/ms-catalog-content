FROM maven:3.9.9-eclipse-temurin-17 AS build
ARG AWS_SECRET_ACCESS_KEY
ARG GH_TOKEN
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN printenv | sort
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
