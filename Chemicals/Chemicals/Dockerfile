FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY Chemicals/Chemicals/pom.xml .
COPY Chemicals/Chemicals/src ./src

RUN mvn clean package -DskipTests

# DEBUG: show what was built
RUN ls -R /app/target

FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy ANY jar produced
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
