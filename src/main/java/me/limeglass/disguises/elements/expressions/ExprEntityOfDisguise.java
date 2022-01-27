package me.limeglass.disguises.elements.expressions;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;

public class ExprEntityOfDisguise extends SimplePropertyExpression<Disguise, Entity> {

	static {
		register(ExprEntityOfDisguise.class, Entity.class, "disguise entity", "disguises");
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@Override
	protected String getPropertyName() {
		return "disguise entity";
	}

	@Override
	@Nullable
	public Entity convert(Disguise disguise) {
		return disguise.getEntity();
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Entity.class);
		return null;
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		getExpr().getSingle(event).setEntity((Entity)delta[0]);
	}

}
