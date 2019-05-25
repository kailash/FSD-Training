From java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASENAME=muzixdb
ENV MONGO_URL=mongodb://localhost:27017/muzixdb
ADD ./target/muzixservice-0.0.1-SNAPSHOT.jar /usr/src/muzixservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","muzixservice-0.0.1-SNAPSHOT.jar"]
