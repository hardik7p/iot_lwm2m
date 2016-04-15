package lwm2m.client;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Temp implements ServletContextListener{
	
	public static String s1 = "Hello World";
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}
 
        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");	
	

	}
}