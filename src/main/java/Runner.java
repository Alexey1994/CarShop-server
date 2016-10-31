import org.restlet.resource.ServerResource;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.Server;
import org.restlet.resource.Directory;
import org.restlet.routing.VirtualHost;


public class Runner extends ServerResource {

    public static void main(String []args) {
        Component   component = new Component();
        Server      server    = component.getServers().add(Protocol.HTTP, 8182);
        Directory   dir       = new Directory(component.getContext().createChildContext(), "file:///d:/Работа/netcracker/Вёрстка");
        VirtualHost host      = component.getDefaultHost();

        host.attach(dir);
        Urls.initRoutes(host);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
