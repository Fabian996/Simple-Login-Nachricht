package de.Fabian.LoginNachticht;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Config {
	static Config instance = new Config();
	  Plugin p;
	  FileConfiguration config;
	  File cfile;

	  public static Config getInstance()
	  {
	    return instance;
	  }

	  public void setup(Plugin p)
	  {
	    this.config = p.getConfig();
	    this.config.options().copyDefaults(true);
	    this.cfile = new File(p.getDataFolder(), "config.yml");
	    saveConfig();
	  }

	  public FileConfiguration getConfig() {
	    return this.config;
	  }

	  public void saveConfig() {
	    try {
	      this.config.save(this.cfile);
	    }
	    catch (IOException e) {
	      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml");
	    }
	  }

	  public void reloadConfig() {
	    this.config = YamlConfiguration.loadConfiguration(this.cfile);
	  }

	  public PluginDescriptionFile getDesc() {
	    return this.p.getDescription();
	  }
	}
