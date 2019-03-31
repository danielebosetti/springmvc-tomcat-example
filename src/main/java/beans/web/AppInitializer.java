package beans.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    servletContext.addListener(new ContextLoaderListener());
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("spring", new DispatcherServlet());
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/*");
  }

}
