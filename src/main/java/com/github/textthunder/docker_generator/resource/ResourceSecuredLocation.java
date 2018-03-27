package com.github.textthunder.docker_generator.resource;

import java.util.Optional;

public class ResourceSecuredLocation {
  SecuredLocationProtocol protocol;
  LocationDomain domain;
  LocationPath path;

  public ResourceSecuredLocation(
    SecuredLocationProtocol protocol,
    LocationDomain domain,
    LocationPath path
  ) {
    this.protocol = protocol;
    this.domain = domain;
    this.path = path;
  }

  public Optional<Resource> get() {
    ResourceCertificate certificate = new ResourceCertificate(
      this.domain.toString()
    );

    return certificate.getConnection().map(
      connection -> new Resource(
        connection,
        this.domain,
        this.path
      )
    );
  }

  @Override
  public String toString() {
    return this.protocol.toString().concat(
      new String(":/")
    ).concat(
      this.domain.toString()
    ).concat(
      this.path.toString()
    );
  }
}
