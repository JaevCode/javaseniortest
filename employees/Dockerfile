FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app/employees

COPY ./employees/.mvn ./.mvn
COPY ./employees/mvnw .
COPY ./employees/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target

COPY ./employees/src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/employees/target/employees-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java","-jar","employees-0.0.1-SNAPSHOT.jar"]