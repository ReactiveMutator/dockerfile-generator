package com.github.textthunder.docker_generator.resource;

import java.nio.file.Path;
import java.util.Optional;
import com.github.textthunder.docker_generator.resource.ResourceSecuredLocation;

public class DockerResource {
  String name;
  ResourceSecuredLocation location;

  public DockerResource(
    String name, 
    ResourceSecuredLocation location
  ) {
    this.name = name;
    this.location = location;
  }

  public Optional<Path> save() {
    return location.get().map(
      connection -> connection.save()
    );
  }
}
