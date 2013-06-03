package com.vartala.soulofw0lf.rpglanguages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;





public final class RPGLanguages extends JavaPlugin implements Listener{

	Map<String, List<String>> playersLanguages = new HashMap<>();
	Map<String, String> activeLanguages = new HashMap<>();
	@Override
	public void onEnable(){
		getCommand("language").setExecutor(new UndeadCommand(this));
		getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		getLogger().info("onEnable has been invoked!");
		this.LoadConfigs();
	}

	public void SaveConfigs(){
		getConfig().createSection("players");
		for (Entry<String, List<String>> entry : this.playersLanguages
				.entrySet()) {
			getConfig().set("players." + entry.getKey() + ".languages",
					entry.getValue());
			getConfig().set("players." + entry.getKey() + ".activelanguage",
					this.activeLanguages.get(entry.getKey()));
		}
		saveConfig();
	}
	private void LoadConfigs() {
		this.playersLanguages.clear();
		this.activeLanguages.clear();
		if (getConfig().contains("players")) {
			for (String name : getConfig().getConfigurationSection("players")
					.getKeys(false)) {
				if (getConfig().contains("players." + name + ".languages")) {
					this.playersLanguages.put(
							name,
							getConfig().getStringList(
									"players." + name + ".languages"));
				}
				if (getConfig().contains("players." + name + ".activelanguage")) {
					this.activeLanguages.put(
							name,
							getConfig().getString(
									"players." + name + ".activelanguage"));
				}
			}
		}

	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		if (!(this.playersLanguages.containsKey(name))) {
			this.playersLanguages.put(name, new ArrayList<String>());

			this.SaveConfigs();
		}
		List<String> j = this.playersLanguages.get(name);
		if(!(j.contains("Common"))){
			this.playersLanguages.get(name)
			.add("Common");
			this.SaveConfigs();
		}
	}
	@Override
	public void onDisable() {
	}
}
