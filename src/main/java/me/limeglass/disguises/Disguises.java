package me.limeglass.disguises;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public final class Disguises extends JavaPlugin {

	private static Disguises instance;
	private SkriptAddon addon;

	@Override
	public void onEnable() {
		instance = this;
		try {
			addon = Skript.registerAddon(this).loadClasses("me.limeglass.disguises", "elements");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static Disguises getInstance() {
		return instance;
	}

}
