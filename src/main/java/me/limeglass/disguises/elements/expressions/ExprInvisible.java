package me.limeglass.disguises.elements.expressions;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;

public class ExprInvisible extends SimplePropertyExpression<FlagWatcher, Boolean> {

	static {
		register(ExprInvisible.class, Boolean.class, "invisibility [state]", "flagwatchers");
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	protected String getPropertyName() {
		return "invisibility state";
	}

	@Override
	@Nullable
	public Boolean convert(FlagWatcher watcher) {
		return watcher.isInvisible();
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		if (getExpr() != null && delta != null)
			getExpr().getSingle(e).setInvisible((Boolean)delta[0]);
	}

}
