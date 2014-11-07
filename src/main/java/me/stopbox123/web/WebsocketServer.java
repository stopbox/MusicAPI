package me.stopbox123.web;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class WebsocketServer extends WebSocketServer {
	public static WebsocketServer s;
	 
    public WebsocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }
 
    public WebsocketServer(InetSocketAddress address) {
        super(address);
    }
 
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        WebsocketSessionManager.getSessionManager().openSession(conn.getRemoteSocketAddress().getAddress().getHostAddress());
        Bukkit.getLogger().info(conn.getRemoteSocketAddress().getAddress().getHostName() + " has connected to the Websocket server!");
    }
 
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        WebsocketSessionManager.getSessionManager().endSession(conn.getRemoteSocketAddress().getAddress().getHostAddress());
        Bukkit.getLogger().info(conn + " has disconnected form the Websocket server");
    }
 
    @Override
    public void onMessage(WebSocket conn, String message) {
        Bukkit.getLogger().info("Recieve Websocket packet - " + conn + ":" + message);
        if (message.split(":")[0].equalsIgnoreCase("name")) {
            WebsocketSessionManager.getSessionManager().addSessionUsername(conn.getRemoteSocketAddress().getAddress().getHostAddress(), message.split(":")[1]);
        }
    }
 
    public static void runServer() throws InterruptedException, IOException {
        WebSocketImpl.DEBUG = true;
        int port = 8887;
        s = new WebsocketServer(port);
        s.start();
        Bukkit.getLogger().info("Websocket server started on port: " + s.getPort());
    }
    
    public static void stopServer() throws IOException, InterruptedException {
    	s.stop();
    }
 
    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        	System.out.println("Cannot connect");
        }
    }
 
    public void sendToAll(String data) {
        Collection<WebSocket> con = connections();
        synchronized (con) {
            for (WebSocket c : con) {
                c.send(data);
            }
        }
    }
 
    public void sendData(WebsocketSession session, String data) {
        Collection<WebSocket> con = connections();
        synchronized (con) {
            for (WebSocket c : con) {
                if (c.getRemoteSocketAddress().getAddress().getHostAddress().equalsIgnoreCase(session.getHost())) {
                    Bukkit.getLogger().info("Send data packet: " + data);
                    c.send(data);
                }
            }
        }
    }

}
