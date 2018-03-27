package com.github.textthunder.docker_generator.image;

public class DockerImage {
  private String name;
  private String tagName;

  public DockerImage(String name, String tagName) {
    this.name = name;
    this.tagName = tagName;
  }

  @Override
  public String toString() {
    if (this.tagName.equals("")) {
      return this.name;
    } else {
      return this.name.concat(
        new String(":")
      ).concat(
        this.tagName
      );
    }
  }
}
