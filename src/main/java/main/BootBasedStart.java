package main;

import org.slf4j.Logger;
import org.springframework.boot.web.servlet.context.XmlServletWebServerApplicationContext;

public class BootBasedStart {
  Logger log;
  public static void main(String[] args) {
    
    XmlServletWebServerApplicationContext context =
        new XmlServletWebServerApplicationContext("classpath:context.xml");
    
    Runtime.getRuntime().addShutdownHook(new Thread( ()->{
      System.out.println("shutting down..");
      context.close();
    }));
  }
}
