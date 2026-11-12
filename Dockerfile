FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

RUN apt-get update && apt-get install -y \
    chromium \
    chromium-driver \
    && rm -rf /var/lib/apt/lists/*

COPY pom.xml .
COPY src ./src
COPY WebPages ./WebPages

CMD ["mvn", "test"]