package launch;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {

    private static Tomcat tomcat;

    public static void main(String[] args) throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(80);
        tomcat.getConnector();

        Context ctx = tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();

        tomcat.getServer().await();
    }
}