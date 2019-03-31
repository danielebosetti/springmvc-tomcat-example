package main;

import beans.server.TomcatWrapper;

public class Tomcat__MainClass {

  public static void main(String[] args) throws Exception {
    System.out.printf("running from dir=%s%n", System.getProperty("user.dir"));
    
    TomcatWrapper.started();
  }
}
