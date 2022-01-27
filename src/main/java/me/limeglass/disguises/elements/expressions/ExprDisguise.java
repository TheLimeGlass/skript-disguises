package me.limeglass.disguises.elements.expressions;

import java.util.Arrays;
import java.util.stream.Stream;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;

public class ExprDisguise extends PropertyExpression<Entity, Disguise> {

	static {
		Skript.registerExpression(ExprDisguise.class, Disguise.class, ExpressionType.COMBINED, "disguise of %entities% [(to|from) player[s] %-players%]", "%entities%'s disguise [(to|from) player %-players%]");
	}

	private Expression<Player> players;

	@Override
	public Class<? extends Disguise> getReturnType() {
		return Disguise.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		players = (Expression<Player>) exprs[1];
		setExpr((Expression<? extends Entity>) exprs[0]);
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (event == null)
			return "disguise of entities";
		return "disguise of " + getExpr().toString(event, debug);
	}

	@Override
	protected Disguise[] get(Event event, Entity[] source) {
		return Arrays.stream(source)
				.flatMap(entity -> {
					if (players == null)
						return Stream.of(DisguiseAPI.getDisguise(entity));
					return Arrays.stream(players.getArray(event)).map(player -> DisguiseAPI.getDisguise(player, entity));
				})
				.filter(disguise -> disguise != null)
				.toArray(Disguise[]::new);
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Disguise.class);
		return null;
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (getExpr() == null || delta == null)
			return;
		if (players != null) {
			for (Entity entity : getExpr().getArray(event))
				DisguiseAPI.disguiseToPlayers(entity, (Disguise)delta[0], players.getArray(event));
			return;
		}
		for (Entity entity : getExpr().getArray(event))
			DisguiseAPI.disguiseToAll(entity, (Disguise)delta[0]);
	}

}
