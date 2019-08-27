package launch;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import rest.AppConfig;

import java.io.File;

public class Main {


    private static Tomcat tomcat;

    public static void main(String[] args) throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        String port = System.getenv("PORT");
        if (port==null){ port="8080"; }

        tomcat.setPort(Integer.parseInt(port));
        tomcat.getConnector();
        tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}