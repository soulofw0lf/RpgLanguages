package com.vartala.soulofw0lf.rpglanguages;


import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener{
	private static final String RAINBOW[] = { "DARK_RED", "RED", "GOLD", "YELLOW", "GREEN", "DARK_GREEN", "AQUA", "DARK_AQUA", "BLUE", "DARK_BLUE", "LIGHT_PURPLE", "DARK_PURPLE" };
	RPGLanguages Chat;
	public PlayerChatListener(RPGLanguages chat){
		this.Chat = chat;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player k = event.getPlayer();
		String s = event.getMessage();
		Set<Player> a = event.getRecipients();
		String name = k.getName(); 
		if (this.Chat.activeLanguages.containsKey(name)){


			if ((this.Chat.getConfig().getString("players." + name + ".activelanguage")) == "Undead"){
				event.setCancelled(true);	
				for (Player p : a){
					List<String> j = this.Chat.playersLanguages.get(p.getName());
					if(j.contains("Undead")){
						p.sendMessage("§F<" + k.getDisplayName() + "§f>" + "§8[Undead]: §F" + s);
					} else {
						if (!(j.contains("Undead"))){
							String v = s.toLowerCase();
							v = v.replaceAll(" ", "~");
							v = v.replaceAll("a", " ");
							v = v.replaceAll("~", "a");
							v = v.replaceAll("e", "~");
							v = v.replaceAll("i", " ");
							v = v.replaceAll("o", "§");
							v = v.replaceAll("~", "o");
							v = v.replaceAll("u", "i");
							v = v.replaceAll("§", "u");
							v = v.replaceAll("b", "~");
							v = v.replaceAll("z", "b");
							v = v.replaceAll("~", "z");
							v = v.replaceAll("c", "~");
							v = v.replaceAll("y", "c");
							v = v.replaceAll("~", "y");
							v = v.replaceAll("d", "~");
							v = v.replaceAll("x", "d");
							v = v.replaceAll("~", "x");
							v = v.replaceAll("f", "~");
							v = v.replaceAll("w", "f");
							v = v.replaceAll("~", "w");
							v = v.replaceAll("g", "~");
							v = v.replaceAll("v", "g");
							v = v.replaceAll("~", "v");
							v = v.replaceAll("h", "~");
							v = v.replaceAll("t", " ");
							v = v.replaceAll("~", "t");
							v = v.replaceAll("j", "~");
							v = v.replaceAll("s", "j");
							v = v.replaceAll("~", "s");
							v = v.replaceAll("k", "~");
							v = v.replaceAll("r", "k");
							v = v.replaceAll("~", "r");
							v = v.replaceAll("l", "~");
							v = v.replaceAll("q", "l");
							v = v.replaceAll("~", "q");
							v = v.replaceAll("m", "~");
							v = v.replaceAll("n", "§");
							v = v.replaceAll("p", "m");
							v = v.replaceAll("~", "n");
							v = v.replaceAll("§", "p");
							p.sendMessage("§f<" + k.getDisplayName() + "§8[Undead] §F>§8" + v);
						}

					}
				}
			}


			if ((this.Chat.getConfig().getString("players." + name + ".activelanguage")) == "Arcane"){
				event.setCancelled(true);	
				for (Player p : a){
					List<String> j = this.Chat.playersLanguages.get(p.getName());
					if(j.contains("Arcane")){
						p.sendMessage("§F<" + k.getDisplayName() + "§f>" + "§b[Arcane]: §F" + s);
					} else {
						if (!(j.contains("Arcane"))){
							String v = s.toLowerCase();
							int z = 0;
							StringBuffer arc = new StringBuffer();
							for (char ane : v.toCharArray()) {
								z = 0 + (int)(Math.random() * (RAINBOW.length));
								arc.append(ChatColor.valueOf(RAINBOW[z]) + "§k" + Character.toString(ane));
							}
							v = arc.toString();
							p.sendMessage(k.getDisplayName() + "§b[Arcane]" + v);
						}
					}

				}
			}
		}
	}
}

