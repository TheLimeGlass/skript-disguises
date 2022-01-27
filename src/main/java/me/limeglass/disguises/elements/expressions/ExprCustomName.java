package me.limeglass.disguises.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;

public class ExprCustomName extends SimplePropertyExpression<FlagWatcher, String> {

	static {
		register(ExprCustomName.class, String.class, "custom name", "flagwatchers");
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	protected String getPropertyName() {
		return "custom name";
	}

	@Override
	@Nullable
	public String convert(FlagWatcher watcher) {
		return watcher.getCustomName();
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(String.class);
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		if (getExpr() != null && delta != null)
			getExpr().getSingle(e).setCustomName((String)delta[0]);
	}

}
