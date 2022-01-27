package me.limeglass.disguises.elements.conditions;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.limeglass.disguises.lang.PropertyCondition;

public class CondCustomName extends PropertyCondition<FlagWatcher> {

	static {
		register(CondCustomName.class, PropertyType.HAVE, "[a] custom name", "flagwatchers");
	}

	@Override
	public boolean check(FlagWatcher watcher) {
		return watcher.hasCustomName();
	}

	@Override
	protected String getPropertyName() {
		return "custom name";
	}

}
