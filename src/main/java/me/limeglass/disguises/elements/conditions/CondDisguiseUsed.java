package me.limeglass.disguises.elements.conditions;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.limeglass.disguises.lang.PropertyCondition;

public class CondDisguiseUsed extends PropertyCondition<Disguise> {

	static {
		register(CondDisguiseUsed.class, "(already|being) used", "disguises");
	}

	@Override
	public boolean check(Disguise disguise) {
		return DisguiseAPI.isDisguiseInUse(disguise);
	}

	@Override
	protected String getPropertyName() {
		return "already used";
	}

}
