# Rover
An application allowing a user to create a world and add a rover which can move around within the bounds of the world.

This application has been dockerised.

In order to get it up and running, please follow the following steps:
1. Either download the repository and extract its contents or simply clone the repository on to your local machine.
2. Using the command line, navigate to the location of the repository on your local machine.
3. Build the Docker image using the following command: 
    ``` docker build -t my-rover-app . ```
4. To run the application, simply execute the following command:
    ``` docker run -it --rm --name my-running-app my-rover-app ```
