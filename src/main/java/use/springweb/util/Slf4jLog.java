package use.springweb.util;

import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLog implements Log {

  final Logger logger;
  
  public Slf4jLog() {
    logger=null;
  }
  
  public Slf4jLog(String name) {
    logger=LoggerFactory.getLogger(name);
  }
  
  @Override
  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  @Override
  public boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  @Override
  public boolean isFatalEnabled() {
    return logger.isErrorEnabled();
  }

  @Override
  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  @Override
  public boolean isTraceEnabled() {
    return logger.isTraceEnabled();
  }

  @Override
  public boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  @Override
  public void trace(Object message) {
    logger.trace("{}", message);
  }

  @Override
  public void trace(Object message, Throwable t) {
    logger.trace("{}", message, t);    
  }

  @Override
  public void debug(Object message) {
    logger.debug("{}", message);    
  }

  @Override
  public void debug(Object message, Throwable t) {
    logger.debug("{}", message, t);    
  }

  @Override
  public void info(Object message) {
    logger.info("{}", message);    
  }

  @Override
  public void info(Object message, Throwable t) {
    logger.info("{}", message, t);    
  }

  @Override
  public void warn(Object message) {
    logger.warn("{}", message);    
  }

  @Override
  public void warn(Object message, Throwable t) {
    logger.warn("{}", message);    
  }

  @Override
  public void error(Object message) {
    logger.error("{}", message);    
  
  }

  @Override
  public void error(Object message, Throwable t) {
    logger.error("{}", message);    
  }

  @Override
  public void fatal(Object message) {
    logger.error("{}", message);    
  }

  @Override
  public void fatal(Object message, Throwable t) {
    logger.error("{}", message, t);    
  }

}
