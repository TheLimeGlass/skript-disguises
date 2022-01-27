package me.limeglass.disguises.elements.conditions;

import org.bukkit.entity.Entity;

import me.libraryaddict.disguise.DisguiseAPI;
import me.limeglass.disguises.lang.PropertyCondition;

public class CondDisguised extends PropertyCondition<Entity> {

	static {
		register(CondDisguised.class, "disguised", "entities");
	}

	@Override
	public boolean check(Entity entity) {
		return DisguiseAPI.isDisguised(entity);
	}

	@Override
	protected String getPropertyName() {
		return "disguised";
	}

}
