FROM maven:3-eclipse-temurin-25 AS build
WORKDIR /workspace

COPY pom.xml ./
COPY src ./src

RUN mvn -q -DskipTests package

FROM eclipse-temurin:25-jre
WORKDIR /app

# Fixed: Explicitly copy the fat JAR containing all dependencies
COPY --from=build /workspace/target/*-jar-with-dependencies.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
