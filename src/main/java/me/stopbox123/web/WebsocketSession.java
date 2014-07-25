package me.stopbox123.web;

public class WebsocketSession {
    String host;
    String name;
 
    public WebsocketSession(String host) {
        this.host = host;
    }
 
    public String getHost() {
        return host;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String toString() {
        return "WebsocketSession.java - Host: " + host + " Name: " + name;
    }
}
