package Urls;

import Security.Coder;
import org.restlet.*;
import org.restlet.data.*;
import org.restlet.resource.*;
import org.restlet.routing.*;

import java.beans.Encoder;


public class Main extends ServerResource {



    public static void main(String []args) {
        //System.out.println(args[0]);

        Component   component = new Component();
        Server      server    = component.getServers().add(Protocol.HTTP, 5000);
        //Server      server    = component.getServers().add(Protocol.HTTP, Integer.parseInt(args[0]));
        Directory   dir       = new Directory(component.getContext().createChildContext(), "file:///c:/carshop/Вёрстка");
        //Directory   dir       = new Directory(component.getContext().createChildContext(), "https://alexey1994.github.io/CarShop-static/");
        VirtualHost host      = component.getDefaultHost();

        //component.getClients().add(Protocol.HTTPS);
        component.getClients().add(Protocol.FILE);
        host.attach(dir);
        Urls.initRoutes(host);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
