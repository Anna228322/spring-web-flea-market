#FROM maven:latest
#WORKDIR .
#RUN mvn package
#ENTRYPOINT ["/bin/bash"]
#CMD ["run.sh"]
FROM openjdk:16-alpine3.13

WORKDIR .

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
#CMD ["run.sh"]