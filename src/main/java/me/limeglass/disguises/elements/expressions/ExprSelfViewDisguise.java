package me.limeglass.disguises.elements.expressions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.DisguiseAPI;

public class ExprSelfViewDisguise extends SimplePropertyExpression<Entity, Boolean> {

	static {
		register(ExprSelfViewDisguise.class, Boolean.class, "(can see self|self view[ing]) disguise [state]", "entities");
	}

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	protected String getPropertyName() {
		return "(can see self|self view[ing]) disguise [state]";
	}

	@Override
	@Nullable
	public Boolean convert(Entity entity) {
		return DisguiseAPI.isViewSelfToggled(entity);
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode){
		if (getExpr() != null && delta != null)
			DisguiseAPI.setViewDisguiseToggled(getExpr().getSingle(event), (Boolean)delta[0]);
	}

}
