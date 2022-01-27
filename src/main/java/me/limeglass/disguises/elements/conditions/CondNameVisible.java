package me.limeglass.disguises.elements.conditions;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.limeglass.disguises.lang.PropertyCondition;

public class CondNameVisible extends PropertyCondition<FlagWatcher> {

	static {
		register(CondNameVisible.class, "name visible", "flagwatchers");
	}

	@Override
	public boolean check(FlagWatcher watcher) {
		return watcher.isCustomNameVisible();
	}

	@Override
	protected String getPropertyName() {
		return "name visible";
	}

}
