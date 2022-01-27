package me.limeglass.disguises.elements.expressions;

import java.util.Arrays;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;

public class ExprClientViewedDisguise extends PropertyExpression<Entity, Disguise> {

	static {
		register(ExprClientViewedDisguise.class, Disguise.class, "[(the|all)] [of] [the] client [viewed] disguises", "entities");
	}

	@Override
	public Class<? extends Disguise> getReturnType() {
		return Disguise.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends Entity>) exprs[0]);
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "client viewed disguises of " + getExpr().toString(event, debug);
	}

	@Override
	protected Disguise[] get(Event event, Entity[] source) {
		return Arrays.stream(source).map(entity -> DisguiseAPI.getDisguises(entity)).toArray(Disguise[]::new);
	}

}
