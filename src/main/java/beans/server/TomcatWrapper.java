package beans.server;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import org.apache.catalina.startup.Tomcat;

public class TomcatWrapper implements StartStopService {
  
  private static final int DEFAULT_PORT = 8080;
  private static final boolean RUNNING_FROM_ECLIPSE = true;
  private final Tomcat tomcat;

  public TomcatWrapper() throws Exception {
    tomcat = new Tomcat();
    tomcat.setPort(DEFAULT_PORT);
    
    String currentDir = System.getProperty("user.dir");
    System.out.println("currentDir="+currentDir);
    
    String webAppDir;
    
    if (RUNNING_FROM_ECLIPSE) {
      webAppDir = Paths.get(currentDir, "target","classes").toAbsolutePath().toString();
    }
    else {
      //TODO
      throw new RuntimeException();
    }
    
    System.out.printf("webAppDir=%s%n", webAppDir);
    tomcat.setBaseDir(webAppDir);
    tomcat.addWebapp("/app", new File(webAppDir).getAbsolutePath());
  }
  
  public static TomcatWrapper started() throws Exception {
    TomcatWrapper res=new TomcatWrapper();
    res.start();
    return res;
  }

  @Override
  public void start() throws Exception {
    new Thread(this::waitUntilStopped).start();
    tomcat.start();
  }

  private final CountDownLatch stop = new CountDownLatch(1);

  private void waitUntilStopped() {
    try {
      stop.await();
    } catch (InterruptedException e) {
      e.printStackTrace(System.out);
    }
  }

  @Override
  public void stop() throws Exception {
    try {
      stop.countDown();
      tomcat.stop();
    } finally {
      tomcat.destroy();
    }
  }
}
