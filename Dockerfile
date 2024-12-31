# ---- Stage 1 : Build ----
    FROM maven:3.9.3-eclipse-temurin-17 AS build
    WORKDIR /app
    
    # Copie du pom.xml et des sources
    COPY pom.xml .
    COPY src ./src
    
    # Compilation du projet et génération du .jar
    RUN mvn clean package -DskipTests
    
    # ---- Stage 2 : Runtime ----
    FROM eclipse-temurin:17-jdk AS runtime
    WORKDIR /app
    
    # Copier le JAR généré depuis l'étape de build
    COPY --from=build /app/target/todo-0.0.1-SNAPSHOT.jar app.jar
    
    # Exposer le port 8080 (port par défaut d'une app Spring Boot)
    EXPOSE 8080
    
    # Lancement de l'application Spring Boot
    ENTRYPOINT ["java", "-jar", "app.jar"]
    