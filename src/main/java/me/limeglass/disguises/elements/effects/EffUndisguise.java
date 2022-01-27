package me.limeglass.disguises.elements.effects;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;

public class EffUndisguise extends Effect {

	static {
		Skript.registerEffect(EffUndisguise.class, "undisguise %entities% [from %entities%]");
	}

	private Expression<Entity> entities, to;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		entities = (Expression<Entity>) exprs[0];
		to = (Expression<Entity>) exprs[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		if (to != null) {
			for (Entity entity : entities.getArray(event))
				DisguiseAPI.undisguiseToAll(entity);
			return;
		}
		for (Entity entity : entities.getArray(event)) {
			for (Entity see : to.getArray(event)) {
				DisguiseAPI.undisguiseToAll(entity, see);
			}
		}
	}

	@Override
	public String toString(Event event, boolean debug) {
		return "undisguise entities " + entities.toString(event, debug);
	}

}
