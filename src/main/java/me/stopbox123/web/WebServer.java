package me.stopbox123.web;

import java.io.File;

import me.stopbox123.server.MusicAPI;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class WebServer {
	public void runServer() {
        Server server = new Server(8080);
 
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] { "index.html" });
 
        new File(MusicAPI.getInstance().getDataFolder(), "httdocs").mkdirs();
        resource_handler.setResourceBase(new File(MusicAPI.getInstance().getDataFolder(), "httdocs").getAbsolutePath());
 
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
        server.setHandler(handlers);
 
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
