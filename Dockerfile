FROM maven:3-eclipse-temurin-25 AS build
WORKDIR /workspace

COPY pom.xml ./
COPY src ./src

RUN mvn -q -DskipTests package

FROM eclipse-temurin:25-jre
WORKDIR /app

RUN mkdir -p /src/main/resources

COPY --from=build /workspace/target/*-jar-with-dependencies.jar /app/app.jar
COPY --from=build /workspace/src/main/resources /src/main/resources

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
