package me.stopbox123.server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import me.stopbox123.web.WebServer;
import me.stopbox123.web.WebsocketServer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

public class MusicAPI extends JavaPlugin {
	
	private static MusicAPI instance;

    File file;

	@Override
	public void onEnable() {
		instance = this;
		try {
			WebsocketServer.runServer();
            WebServer.runServer();
		} catch (Exception e) {
		}

        file = new File(getDataFolder() + "/htdocs");

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                if (!file.exists()) {

                }
            }
        }, 20L);
    }

	public static MusicAPI getInstance() {
		return instance;
	}

}
