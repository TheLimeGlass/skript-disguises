package me.limeglass.disguises.elements.conditions;

import org.bukkit.entity.Player;

import me.libraryaddict.disguise.DisguiseAPI;
import me.limeglass.disguises.lang.PropertyCondition;

public class CondSelfDisguised extends PropertyCondition<Player> {

	static {
		register(CondSelfDisguised.class, "self disguised", "players");
	}

	@Override
	public boolean check(Player player) {
		return DisguiseAPI.isSelfDisguised(player);
	}

	@Override
	protected String getPropertyName() {
		return "self disguised";
	}

}
