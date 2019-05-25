From java:8-jre
WORKDIR usr/lib
ENV MYSQL_DATABASE=UserDb
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=root123
ENV MYSQL_URL=jdbc:mysql://localhost:3306/UserDb
ADD ./target/authenticationservice-0.0.1-SNAPSHOT.jar /usr/lib/authenticationservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authenticationservice-0.0.1-SNAPSHOT.jar"]

