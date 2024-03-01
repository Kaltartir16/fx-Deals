# FX Deals

## Description
FX Deals is a Spring Boot application designed for analyzing and processing foreign exchange (FX) deals. It offers functionalities for validating, storing, and analyzing FX deal information efficiently.

## Features
- Process and validate FX deals
- Store and retrieve deal information
- Analyze FX deal data

## Docker Support
This project includes a Dockerfile, allowing you to build a Docker image and run the application within a Docker container. This approach simplifies deployment and ensures consistent runtime environments.

### Building the Docker Image
To build the Docker image:

1. Navigate to the project's root directory:

    ```bash
    cd path/to/fx-deals
    ```

2. Run the Docker build command:

    ```bash
    docker build -t fx-deals .
    ```

   This builds a Docker image named fx-deals based on the Dockerfile.

### Running the Application in a Docker Container
To run the application in a Docker container:

1. Start the Docker container:

    ```bash
    docker run -p 8080:8080 fx-deals
    ```

   This command runs the application inside the Docker container, mapping port 8080 of the container to port 8080 on your host machine.

2. Access the application at [http://localhost:8080](http://localhost:8080).

## Getting Started
### Prerequisites
- Java JDK 17 or later
- Maven
- Spring Boot
- Docker (optional for Docker-based setup)

### Installing and Running Locally
1. Clone the repository:

    ```bash
    git clone https://github.com/Kaltartir16/fx-deals
    ```

2. Navigate to the project directory:

    ```bash
    cd fx-deals
    ```

3. Compile and package the application using Maven:

    ```bash
    mvn clean package
    ```

4. Run the application:

    ```bash
    java -jar fx-deals-0.0.1-SNAPSHOT.jar
    ```

## Authors
Khader AlTartir - Initial work - [Kaltartir16](https://github.com/Kaltartir16)

## Version History
- 0.1: Initial Release
