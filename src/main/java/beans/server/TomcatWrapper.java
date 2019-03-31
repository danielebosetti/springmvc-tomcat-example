package beans.server;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import org.apache.catalina.startup.Tomcat;

public class TomcatWrapper implements StartStopService {

  private static final int DEFAULT_PORT = 8080;
  private final Tomcat tomcat;

  public TomcatWrapper() throws Exception {
    tomcat = new Tomcat();
    tomcat.setPort(DEFAULT_PORT);
    String webAppDir = System.getProperty("java.io.tmpdir");
    System.out.printf("webAppDir=%s%n", webAppDir);
    webAppDir="C:\\bin\\apps\\eclipse-java-2018-12-R-win32-x86_64\\ws\\use-springweb\\target\\classes";
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
