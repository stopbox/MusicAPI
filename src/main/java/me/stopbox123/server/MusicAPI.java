package me.stopbox123.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import me.stopbox123.web.WebServer;
import me.stopbox123.web.WebsocketServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MusicAPI extends JavaPlugin {
	
	private static MusicAPI instance;
	private static WebsocketServer server;
	
	@Override
	public void onEnable() {
		instance = this;
		server = new WebsocketServer(new InetSocketAddress(Bukkit.getServer().getIp(), 8080));
		try {
			server.runServer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {
		try {
			server.stopServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static MusicAPI getInstance() {
		return instance;
	}

}
