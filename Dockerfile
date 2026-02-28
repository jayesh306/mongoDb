#-----------Stage-1 Build-----------
FROM maven: 3.9.9-eclipse-termurin-21 AS build

#Work directory
WORKDIR /workspace

#Copy pom first to leverage Docker layer caching
COPY pom.xml

#Pre-fetch dependencies (faster incremental builds)
RUN mvn -q -e -DskipTests dependency:go-offline

#Copy source and build the app
COPY src/ src/
RUN mvn -q -DskipTests clean package

#------------Stage 2---------------
FROM eclipse-termurin:21-jre-alpine

#Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

#Copy the built jar from the building stage
#If your jar name is customized, adjust the pattern
COPY --from=build /workspace/target/*.jar /app/app.jar

#Container port
EXPOSE 8080


# Recommended JVM container flags (override via -e JAVA_OPTS="...")
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Prefer passing Mongo URI via env var for containers:
# For Spring Boot 4.x, use SPRING_MONGODB_URI (maps to spring.mongodb.uri)
# Example at runtime:
#   -e SPRING_MONGODB_URI="mongodb://mongo:27017/ecommerce"
#
# (You can still use SPRING_DATA_MONGODB_URI if you keep that property,
# but for Boot 4+ prefer SPRING_MONGODB_URI.)

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
