FX Deals
Description
FX Deal  is a Spring Boot application designed for analyzing and processing foreign exchange (FX) deals. It offers functionalities for validating, storing, and analyzing FX deal information efficiently.

Features
Process and validate FX deals
Store and retrieve deal information
Analyze FX deal data
Docker Support
This project includes a Dockerfile, allowing you to build a Docker image and run the application within a Docker container. This approach simplifies deployment and ensures consistent runtime environments.

Building the Docker Image
To build the Docker image:

Navigate to the project's root directory:

cd path/to/fx-deals
Run the Docker build command:

docker build -t fx-deals .
This builds a Docker image named fxdeals based on the Dockerfile.

Running the Application in a Docker Container
To run the application in a Docker container:

Start the Docker container:

docker run -p 8080:8080 fxdeals
This command runs the application inside the Docker container, mapping port 8080 of the container to port 8080 on your host machine.

Access the application at http://localhost:8080.

Getting Started
Prerequisites
Java JDK 17 or later
Maven
Spring Boot
Docker (optional for Docker-based setup)
Installing and Running Locally
Clone the repository:

git clone https://github.com/Kaltartir16/fx-deals
Navigate to the project directory:

cd fx-deals
Compile and package the application using Maven:

mvn clean package
Run the application:

java -jar fx-deals-0.0.1-SNAPSHOT.jar
Authors
Khader AlTartir - Initial work - Kaltartir16
Version History
0.1 - Initial Release
