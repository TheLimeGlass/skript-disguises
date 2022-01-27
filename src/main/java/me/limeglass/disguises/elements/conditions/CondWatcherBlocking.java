package me.limeglass.disguises.elements.conditions;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.limeglass.disguises.lang.PropertyCondition;

public class CondWatcherBlocking extends PropertyCondition<FlagWatcher> {

	static {
		register(CondWatcherBlocking.class, "(right clicking|blocking)", "flagwatchers");
	}

	@Override
	public boolean check(FlagWatcher watcher) {
		return watcher.isRightClicking();
	}

	@Override
	protected String getPropertyName() {
		return "blocking";
	}

}
