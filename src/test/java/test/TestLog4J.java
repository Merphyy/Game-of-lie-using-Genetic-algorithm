package test;

import org.apache.log4j.Logger;

public class TestLog4J {
	
	final static Logger logger = Logger.getLogger(TestLog4J.class);  
	  
	 public static void main(String[] args) { 
//	  logger.debug("should not show message"); 
//	  logger.warn("should show some message");
//	  logger.fatal("should show some message");
	  
	  
	  logger.info(" Result !!!!");
	 }

}
