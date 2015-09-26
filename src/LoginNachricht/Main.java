package de.Fabian.LoginNachticht;

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

public class main extends JavaPlugin implements Listener{

	Config settings = Config.getInstance();
	
	//onEnable
	public void onEnable(){
		this.settings.setup(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		saveConfig();
		
	
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
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if(p.hasPermission("loginachricht.default"))
		{
		e.setJoinMessage(ChatColor.AQUA + (this.settings.getConfig().getString("message").replace("%p%", p.getName())));
		}
	}
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		if(p.hasPermission("loginachricht.default"))
		{
		e.setQuitMessage(ChatColor.DARK_GREEN + (this.settings.getConfig().getString("leftmessage")).replace("%p%", p.getName()));
		}
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (label.equalsIgnoreCase("ln")) 
	    {
	      if (args.length == 1) {
	      if (args[0].equalsIgnoreCase("reload")) 
	        {
	        this.settings.reloadConfig();
	        sender.sendMessage("§7 config reloaded!");
	        }
	        else 
	        {	         
	        sender.sendMessage("§cUsage: /ln <Reload>");
	        }
	      }
	      else sender.sendMessage("§cUsage: /ln <Reload>");
	    }

	    return true;
	  }

}
