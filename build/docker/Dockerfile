FROM dockerfile/java:openjdk-11-slim
LABEL maintainer="Kipras Sivickas \"sivickaskipras@gmail.com\""
WORKDIR /app
COPY libs libs/
COPY resources resources/
COPY classes classes/
ENTRYPOINT ["java", "-Xms256m", "-Xmx2048m", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.example.WS1.Ws1Application"]
EXPOSE 8080
