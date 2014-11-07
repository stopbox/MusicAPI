package me.stopbox123.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import me.stopbox123.web.WebServer;
import me.stopbox123.web.WebsocketServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MusicAPI extends JavaPlugin {
	
	private static MusicAPI instance;
	
	@Override
	public void onEnable() {
		instance = this;
		try {
			WebsocketServer.runServer();
            WebServer.runServer();
		} catch (Exception e) {
		}
	}

	public static MusicAPI getInstance() {
		return instance;
	}

}
