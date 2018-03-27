package com.github.textthunder.docker_generator;

import java.io.*;
import java.nio.file.*;
import java.util.Optional;
import static java.nio.file.StandardOpenOption.*;

import com.github.textthunder.docker_generator.dockerfile.*;
import com.github.textthunder.docker_generator.image.*;
import com.github.textthunder.docker_generator.resource.*;

public class DockerGenerator {
  public static void main(String arguments[]) {
    System.out.println("Dockerfiles generator");

    Path dockerfile = Paths.get("Dockerfile");

    Dockerfile alpineMinimal = new Dockerfile(
      new DockerImage("scratch", "")
    );
    
    DockerResource resource = new DockerResource(
      "alpine-minimal", new ResourceSecuredLocation(
        SecuredLocationProtocol.HTTPS,
        new LocationDomain(
          new String[]{
            "mirror",
            "csclub",
            "uwaterloo",
            "ca"
          }
        ),
        new LocationPath(
          new String[]{
            "alpine",
            "v3.7",
            "releases",
            "x86_64",
            "alpine-minirootfs-3.7.0-x86_64.tar.gz"
          }
        )
      )
    );

    Optional<Path> pathToSavedResource = resource.save();

    pathToSavedResource.map(
      path -> {
        System.out.println(path);
        return true;
      }
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
