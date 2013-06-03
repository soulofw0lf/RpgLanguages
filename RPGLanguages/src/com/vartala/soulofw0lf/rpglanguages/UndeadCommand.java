package com.vartala.soulofw0lf.rpglanguages;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UndeadCommand implements CommandExecutor {
	RPGLanguages Chat;

	public UndeadCommand(RPGLanguages chat) {
		this.Chat = chat;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("language")) {

			if (args[0].equalsIgnoreCase("add")) {
				Player p = Bukkit.getPlayer(args[2]);
				if (p == null) {
					sender.sendMessage("Could Not Find Player!!");
					return true;
				}
				String name = p.getName();
				if (!this.Chat.playersLanguages.containsKey(name)) {
					this.Chat.playersLanguages.put(name,
							new ArrayList<String>());
				}
				
				if (this.Chat.playersLanguages.get(name).contains(args[1])) {
					return true;
				} else {
					this.Chat.playersLanguages.get(name).add(args[1]);
				}
				sender.sendMessage("The language " + args[1] + " has been added to " + args[2] + ".");
				this.Chat.SaveConfigs();
				return true;
			}
			if (args[0].equalsIgnoreCase("list")) {
				if (args.length == 2) {
					try {

						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							sender.sendMessage("Could Not Find Player!!");
							return true;
						}

						List<String> s = this.Chat.getConfig().getStringList(
								"players." + p.getName() + ".languages");

						for (String players : s) {

							sender.sendMessage(players + "\n");
						}

						return true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					sender.sendMessage("Improper command usage. Please use /language list {playername}");
					return true;
				}
			}

			if (args[0].equalsIgnoreCase("undead")) {
				StringBuilder buffer = new StringBuilder();

				// change the starting i value to pick what argument to start
				// from
				// 1 is the 2nd argument.
				for (int i = 2; i < args.length; i++) {
					buffer.append(' ').append(args[i]);
				}
				Player player = Bukkit.getPlayer(args[1]);
				List<String> j = this.Chat.playersLanguages.get(player
						.getName());
				String s = buffer.toString();
				if (j.contains("Undead")) {
					player.sendMessage("§f[§8Undead§f]§8" + s);
					return true;
				} else {

					s = s.replaceAll(" ", "~");
					s = s.replaceAll("a", " ");
					s = s.replaceAll("~", "a");
					s = s.replaceAll("e", "~");
					s = s.replaceAll("i", " ");
					s = s.replaceAll("o", "§");
					s = s.replaceAll("~", "o");
					s = s.replaceAll("u", "i");
					s = s.replaceAll("§", "u");
					s = s.replaceAll("b", "~");
					s = s.replaceAll("z", "b");
					s = s.replaceAll("~", "z");
					s = s.replaceAll("c", "~");
					s = s.replaceAll("y", "c");
					s = s.replaceAll("~", "y");
					s = s.replaceAll("d", "~");
					s = s.replaceAll("x", "d");
					s = s.replaceAll("~", "x");
					s = s.replaceAll("f", "~");
					s = s.replaceAll("w", "f");
					s = s.replaceAll("~", "w");
					s = s.replaceAll("g", "~");
					s = s.replaceAll("v", "g");
					s = s.replaceAll("~", "v");
					s = s.replaceAll("h", "~");
					s = s.replaceAll("t", "h");
					s = s.replaceAll("~", "t");
					s = s.replaceAll("j", "~");
					s = s.replaceAll("s", "j");
					s = s.replaceAll("~", "s");
					s = s.replaceAll("k", "~");
					s = s.replaceAll("r", "k");
					s = s.replaceAll("~", "r");
					s = s.replaceAll("l", "~");
					s = s.replaceAll("q", "l");
					s = s.replaceAll("~", "q");
					s = s.replaceAll("m", "~");
					s = s.replaceAll("n", "§");
					s = s.replaceAll("p", "m");
					s = s.replaceAll("~", "n");
					s = s.replaceAll("§", "p");
					player.sendMessage("§f[§8Undead§f]§8" + s);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("set")) {
				Player player = (Player) sender;
				if (args.length != 2) {
					sender.sendMessage("Please use </language set {language_name}> to set your current language");
					return true;
				} else {

					if (player.hasPermission("language.set")) {
						if (args[1].equalsIgnoreCase("Common")) {
							args[1] = "Common";
							this.Chat.activeLanguages.remove(player.getName());
							this.Chat.activeLanguages.put(player.getName(),
									args[1]);
							this.Chat.SaveConfigs();
							sender.sendMessage("you are now speaking "
									+ args[1]);
							return true;
						}
						List<String> j = this.Chat.playersLanguages.get(player
								.getName());
						if (args[1].equalsIgnoreCase("Undead")) {
							args[1] = "Undead";
							if (j.contains(args[1])) {
								this.Chat.activeLanguages.remove(player.getName());
								this.Chat.activeLanguages.put(player.getName(),
										args[1]);
								sender.sendMessage("You are now speaking "
										+ args[1]);

								this.Chat.SaveConfigs();
								return true;
							} else {
								sender.sendMessage("You do not know the language "
										+ args[1]);
								return true;
							}
						}
						if (args[1].equalsIgnoreCase("Arcane")) {
							args[1] = "Arcane";
							if (j.contains(args[1])) {
								this.Chat.activeLanguages.remove(player.getName());
								this.Chat.activeLanguages.put(player.getName(),
										args[1]);
								sender.sendMessage("You are now speaking "
										+ args[1]);

								this.Chat.SaveConfigs();
								return true;
							} else {
								sender.sendMessage("You do not know the language "
										+ args[1]);
								return true;
							}
						}
					} else {
						sender.sendMessage("You do not have permission to use this command!");
						return true;
					}
				}
			}

		}
		return false;
	}
}