package com.github.textthunder.docker_generator;

import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

import com.github.textthunder.docker_generator.dockerfile.*;
import com.github.textthunder.docker_generator.image.*;

public class DockerGenerator {
  public static void main(String arguments[]) {
    System.out.println("Dockerfiles generator");

    Path dockerfile = Paths.get("Dockerfile");

    Dockerfile alpineMinimal = new Dockerfile(
      new DockerImage("scratch", "")
    );
    byte inputData[] = alpineMinimal.toString().getBytes();

    try {
      OutputStream output = new BufferedOutputStream(
        Files.newOutputStream(dockerfile, CREATE)
      );
      output.write(inputData, 0, inputData.length);
      output.close();
    } catch (IOException error) {
      System.err.println(error);
    }
  }
}
