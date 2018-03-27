package com.github.textthunder.docker_generator.resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Resource {
  SocketIO socketIO;
  LocationDomain domain;
  LocationPath relativeURL;

  public Resource(
    SocketIO socketIO,
    LocationDomain domain,
    LocationPath relativeURL
  ) {
    this.socketIO = socketIO;
    this.domain = domain;
    this.relativeURL = relativeURL;
  }

  public Path save() {
    String randomName = Double.toString(
      (new Random()).nextGaussian()
    );

    String tmpFileName = (
      new String("./tmp/")
    ).concat(randomName);

    byte[] buffer = new byte[4096];
    int readBytes = -1;
    FileOutputStream output = null;

    try {
      output = new FileOutputStream(tmpFileName);

      String request = (
        new String ("GET ")
      ).concat(
        this.relativeURL.toString()
      ).concat(
        " HTTP/1.1\r\n"
      ).concat(
        "Host: "
      ).concat(
        this.domain.toString()
      ).concat(
        "\r\n\r\n"
      );

      this.socketIO.output.write(
        request.getBytes()
      );
      
      do {
        readBytes = this.socketIO.socket.getInputStream().read(buffer);

        if (readBytes != -1) {
          output.write(buffer, 0, readBytes);
        }
      } while(readBytes != -1);

      output.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }

    return Paths.get(tmpFileName);
  }
}
