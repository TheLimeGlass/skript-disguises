package me.limeglass.disguises.elements.expressions;

import org.bukkit.event.Event;

import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.limeglass.disguises.lang.SetEffect;

public class EffWatcherBlocking extends SetEffect<FlagWatcher> {

	static {
		register(EffWatcherBlocking.class, "(right clicking|blocking) [animation]", "flagwatchers");
	}

	@Override
	protected String getPropertyName() {
		return "blocking";
	}

	@Override
	protected void execute(Event event) {
		for (FlagWatcher watcher : getExpression().getArray(event))
			watcher.setRightClicking(getBoolean(event));
	}

}
