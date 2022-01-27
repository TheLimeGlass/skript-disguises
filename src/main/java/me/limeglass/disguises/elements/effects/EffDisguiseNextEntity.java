package me.limeglass.disguises.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;

public class EffDisguiseNextEntity extends Effect {

	static {
		Skript.registerEffect(EffDisguiseNextEntity.class,  "[set] disguise [of] next [spawned] [entity] (as|to) %disguise%");
	}

	private Expression<Disguise> disguise;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		disguise = (Expression<Disguise>) e[0];
		return true;
	}
	@Override
	public String toString(Event event, boolean debug) {
		return "disguise next entity as " + disguise.toString(event, debug);
	}
	@Override
	protected void execute(Event e) {
		if (disguise != null)
			DisguiseAPI.disguiseNextEntity(disguise.getSingle(e));
	}
}
