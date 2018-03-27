package com.github.textthunder.docker_generator.resource;

import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.net.ssl.*;

public class ResourceCertificate {
  String domain;

  public ResourceCertificate(
    String domain
  ) {
    this.domain = domain;
  }

  public Optional<SocketIO> getConnection() {
    Optional<SocketIO> socketIO = Optional.empty();
    SSLSocket socket = null;
    try {
      SSLContext sslContext = SSLContext.getInstance("SSL");

      sslContext.init(null, null, null);

      SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
      socket = (SSLSocket) sslSocketFactory.createSocket(
        this.domain.toString(),
        443
      );

      socket.startHandshake();

      X509Certificate[] peerCertificates = (
        X509Certificate[]
      ) socket.getSession().getPeerCertificates();

      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
        TrustManagerFactory.getDefaultAlgorithm()
      );

      trustManagerFactory.init((KeyStore) null);

      X509TrustManager trustManager = (
        X509TrustManager
      ) trustManagerFactory.getTrustManagers()[0];

      trustManager.checkServerTrusted(peerCertificates, "RSA");

      socketIO = Optional.of(
        new SocketIO(
          socket,
          socket.getInputStream(),
          socket.getOutputStream()
        )
      );
    } catch(Exception exception) {
      System.err.println(exception);
      socketIO = Optional.empty();
    }

    return socketIO;
  }
}
