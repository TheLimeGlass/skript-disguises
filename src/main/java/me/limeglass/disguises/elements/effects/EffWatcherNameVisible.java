package me.limeglass.disguises.elements.effects;

import org.bukkit.event.Event;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.limeglass.disguises.lang.SetEffect;

public class EffWatcherNameVisible extends SetEffect<FlagWatcher> {

	static {
		register(EffWatcherNameVisible.class, "name visible", "flagwatchers");
	}

	@Override
	protected String getPropertyName() {
		return "name visible";
	}

	@Override
	protected void execute(Event event) {
		for (FlagWatcher watcher : getExpression().getArray(event))
			watcher.setCustomNameVisible(getBoolean(event));
	}

}
