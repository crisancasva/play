#etapa 1 Build
FROM gradle:9.2.1-jdk21 AS build
COPY --chowm=gradle:gradle . /app
WORKDIR /app
RUN gradle bootjar --no-daemon

#Etapa 2 Runtime con jdk21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar play.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "play.jar"]