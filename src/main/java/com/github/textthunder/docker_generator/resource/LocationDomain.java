package com.github.textthunder.docker_generator.resource;

public class LocationDomain {
  String[] names;

  public LocationDomain(String[] names) {
    this.names = names;
  }

  @Override
  public String toString() {
    return String.join(".", this.names);
  }
}
