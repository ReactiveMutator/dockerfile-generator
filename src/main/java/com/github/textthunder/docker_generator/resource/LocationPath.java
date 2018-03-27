package com.github.textthunder.docker_generator.resource;

public class LocationPath {
  String[] nodes;

  public LocationPath(String[] nodes) {
    this.nodes = nodes;
  }

  @Override
  public String toString() {
    return (new String("/")).concat(
      String.join("/", this.nodes)
    );
  }
}
