package me.stopbox123.server;

import me.stopbox123.web.WebServer;

import org.bukkit.plugin.java.JavaPlugin;

public class MusicAPI extends JavaPlugin {
	
	private static MusicAPI instance;
	private static WebServer server;
	
	@Override
	public void onEnable() {
		instance = this;
		WebServer.runServer();
	}

	public static MusicAPI getInstance() {
		return instance;
	}

}
