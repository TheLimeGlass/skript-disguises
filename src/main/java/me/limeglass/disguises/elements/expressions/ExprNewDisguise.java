package me.limeglass.disguises.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

public class ExprNewDisguise extends SimpleExpression<Disguise> {

	static {
		Skript.registerExpression(ExprNewDisguise.class, Disguise.class, ExpressionType.COMBINED, "[a] [new] %disguisetype% disguise [as item %-itemtype%] [with [user[ ]]name %-string%] [(and|with) baby [state] %-boolean%]");
	}

	private Expression<DisguiseType> type;
	private Expression<ItemType> item;
	private Expression<Boolean> baby;
	private Expression<String> name;

	public Class<? extends Disguise> getReturnType() {
		return Disguise.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
		type = (Expression<DisguiseType>) exprs[0];
		item = (Expression<ItemType>) exprs[1];
		name = (Expression<String>) exprs[2];
		baby = (Expression<Boolean>) exprs[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		return "new disguise " + type.toString(event, debug);
	}

	@Override
	@Nullable
	protected Disguise[] get(Event event) {
		if (type == null)
			return null;
		DisguiseType type = this.type.getSingle(event);
		if (type == null) {
			Skript.info("The entity type was null");
			return null;
		}
		if (type.isMisc()) {
			if (item != null)
				return CollectionUtils.array(new MiscDisguise(type, item.getSingle(event).getRandom()));
			return CollectionUtils.array(new MiscDisguise(type));
		} else if (type.isMob()) {
			if (baby != null)
				return CollectionUtils.array(new MobDisguise(type, baby.getSingle(event)));
			return CollectionUtils.array(new MobDisguise(type));
		} else if (type.isPlayer()) {
			if (name != null)
				return CollectionUtils.array(new PlayerDisguise(name.getSingle(event)));
			return CollectionUtils.array(new PlayerDisguise("Notch"));
		}
		return null;
	}

}
