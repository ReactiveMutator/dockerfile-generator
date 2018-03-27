package com.github.textthunder.docker_generator.resource;

import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLSocket;

public class SocketIO {
  public SSLSocket socket;
  public InputStream input;
  public OutputStream output;

  public SocketIO(
    SSLSocket socket,
    InputStream input,
    OutputStream output
  ) {
    this.socket = socket;
    this.input = input;
    this.output = output;
  }
}
