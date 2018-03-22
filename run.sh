#!/bin/bash

mvn package
java -cp target/docker-generator-0.0.1.jar com.github.textthunder.docker_generator.DockerGenerator; docker build .
