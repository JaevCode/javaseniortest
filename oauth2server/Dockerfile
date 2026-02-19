FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app/oauth2server

COPY ./oauth2server/.mvn ./.mvn
COPY ./oauth2server/mvnw .
COPY ./oauth2server/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target

COPY ./oauth2server/src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/oauth2server/target/oauth2server-0.0.1-SNAPSHOT.jar .

EXPOSE 8082

CMD ["java","-jar","oauth2server-0.0.1-SNAPSHOT.jar"]