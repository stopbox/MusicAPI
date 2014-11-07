package me.stopbox123.server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;

import me.stopbox123.gzip.UnZip;
import me.stopbox123.web.WebServer;
import me.stopbox123.web.WebsocketServer;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

public class MusicAPI extends JavaPlugin {
	
	private static MusicAPI instance;

    File f;

	@Override
	public void onEnable() {
		instance = this;

        f = new File(getDataFolder() + "/htdocs/MCSrv.zip");

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                if (!f.exists()) {
                    try {
                        FileUtils.copyURLToFile(new URL("http://download940.mediafire.com/4z031cx2gfog/77ed2t4x51i4qml/MCWebSrv.zip"), f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 20L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                UnZip unzip = new UnZip();
                unzip.unZip(getDataFolder() + "/htdocs/MCSrv.zip", getDataFolder() + "/htdocs");
            }
        }, 40L);

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
