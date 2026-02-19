FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app/users

COPY ./users/.mvn ./.mvn
COPY ./users/mvnw .
COPY ./users/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target

COPY ./users/src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/users/target/users-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

CMD ["java","-jar","users-0.0.1-SNAPSHOT.jar"]