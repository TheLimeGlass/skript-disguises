package me.limeglass.disguises.elements.expressions;

import java.util.Arrays;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;

public class ExprWatcherArmour extends PropertyExpression<FlagWatcher, ItemStack> {

	static {
		register(ExprWatcherArmour.class, ItemStack.class, "[flag] watcher armour", "flagwatchers");
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends FlagWatcher>) exprs[0]);
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean debug) {
		if (debug || event == null)
			return "Armour of flag watcher";
		return "Armour of flag watcher " + getExpr().toString(event, debug);
	}

	@Override
	protected ItemStack[] get(Event event, FlagWatcher[] source) {
		return Arrays.stream(source).flatMap(watcher -> Arrays.stream(watcher.getArmor())).toArray(ItemStack[]::new);
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		return CollectionUtils.array(ItemStack.class, ItemStack[].class);
	}

	private final static ItemStack[] reset = new ItemStack[] {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)};
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		ItemStack[] items = (ItemStack[]) delta;
		switch (mode) {
			case ADD:
				for (FlagWatcher watcher : getExpr().getArray(event)) {
					ItemStack[] existing = watcher.getArmor();
					watcher.setArmor(mergeArray(existing, items));
				}
				break;
			case RESET:
			case DELETE:
				for (FlagWatcher watcher : getExpr().getArray(event))
					watcher.setArmor(reset);
				break;
			case REMOVE_ALL:
			case REMOVE:
				for (FlagWatcher watcher : getExpr().getArray(event)) {
					ItemStack[] existing = watcher.getArmor();
					for (int i = 0; i < existing.length; i++) {
						for (ItemStack delt : items) {
							if (existing[i].isSimilar(delt))
								existing[i] = new ItemStack(Material.AIR);
						}
					}
					watcher.setArmor(existing);
				}
				break;
			case SET:
				for (FlagWatcher watcher : getExpr().getArray(event))
					watcher.setArmor(items);
				break;
			default:
				break;
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T[] mergeArray(T[] arr1, T[] arr2) {   
		return (T[]) Stream.of(arr1, arr2).flatMap(Stream::of).toArray();   
	}

}
