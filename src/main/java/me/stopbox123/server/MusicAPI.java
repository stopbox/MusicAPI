package me.stopbox123.server;

import me.stopbox123.web.WebsocketServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MusicAPI extends JavaPlugin {
	
	private static MusicAPI instance;

	@Override
	public void onEnable() {
		instance = this;

        saveDefaultConfig();
        saveConfig();
        sfdfdfsfsf

        try {
            WebsocketServer.runServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static MusicAPI getInstance() {
        return instance;
    }

}
