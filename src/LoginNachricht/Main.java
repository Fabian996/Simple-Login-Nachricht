package de.Fabian996.LoginNachticht;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.Fabian996.LoginNachticht.Updater;

public class main extends JavaPlugin implements Listener{

	Config settings = Config.getInstance();
	
	Updater updater = new Updater(this, 95083, getFile(), Updater.UpdateType.DEFAULT, true);
	
	//onEnable
	public void onEnable(){
		this.settings.setup(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		saveConfig();
		
		// Updater
	    Boolean AutoUpdate = Boolean.valueOf(getConfig().getBoolean("ln.AutoUpdate", false));
	    if (AutoUpdate.booleanValue())
	    {
	      if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE)
	        System.out.println("[Login Nachricht] New version avaible!");
	      else if (updater.getResult() == Updater.UpdateResult.NO_UPDATE)
	      {
	        System.out.println("[Login Nachricht]No new version avaible!");
	      }
	    }
	    loadConfig();
	    reloadConfig();
	
		// Version
	    PluginDescriptionFile tml = getDescription();
	    Logger tmllog = getLogger();
	    tmllog.info(tml.getName() + " has been enabled (V." + tml.getVersion() + ")");
	}
	
	//onDisable
	public void onDisable(){
		//Version
	    PluginDescriptionFile tml = getDescription();
	    Logger tmllog = getLogger();
	    tmllog.info(tml.getName() + " has been disable (V." + tml.getVersion() + ")");
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if ((p.hasPermission("login.default"))) {
			e.setJoinMessage(ChatColor.AQUA + (this.settings.getConfig().getString("message").replace("%p%", p.getName())));
		}
	}
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if ((p.hasPermission("login.default"))) {
			e.setQuitMessage(ChatColor.DARK_GREEN + (this.settings.getConfig().getString("leftmessage")).replace("%p%", p.getName()));
		}	
	}
	   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (label.equalsIgnoreCase("ln")) {
	      if (args.length == 1) {
	        if (args[0].equalsIgnoreCase("reload")) {
	          this.settings.reloadConfig();
	          sender.sendMessage("§7 Config reloaded!");
	        } else {
	          sender.sendMessage("§cUsage: /ln <Reload>");
	        }
	      }
	      else sender.sendMessage("§cUsage: /ln <Reload>");

	    }

	    return true;
	  }
	   
	   private void loadConfig() {
		    getConfig().options().header("|----------------|\n|Login Nachtricht|\n|----------------|");
		    getConfig().options().copyDefaults(true);


		    String Update = "ln.AutoUpdate";
		    getConfig().addDefault(Update, Boolean.valueOf(false));
		    getConfig().addDefault("message", "Willkommen %p% , Viel Spaß beim Spielen auf.");
		    getConfig().addDefault("leftmessage", "%p% hat den Server  verlassen. ");

		    saveConfig();
		  }
}
