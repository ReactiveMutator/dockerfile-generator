package com.github.textthunder.docker_generator.resource;

public enum SecuredLocationProtocol {
  HTTPS,
  SFTP;

  @Override
  public String toString() {
    switch (this) {
      case HTTPS:
        return "https";
      case SFTP:
        return "sftp";
      default:
        return "";
    }
  }
}
