package com.github.textthunder.docker_generator.dockerfile;

import com.github.textthunder.docker_generator.image.*;

public class Dockerfile {
  private DockerImage baseImage;

  public Dockerfile(DockerImage baseImage) {
    this.baseImage = baseImage;
  }

  @Override
  public String toString() {
    return (
      new String("FROM")
    ).concat(
      new String(" ")
    ).concat(
      this.baseImage.toString()
    ).concat("\n");
  }
}
