FROM maven:3.6.0-jdk-11-slim
COPY . /usr/src/githubproxy
WORKDIR /usr/src/githubproxy
RUN mvn install

EXPOSE 80

CMD ["mvn", "exec:java"]


