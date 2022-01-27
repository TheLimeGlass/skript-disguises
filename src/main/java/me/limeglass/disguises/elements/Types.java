package me.limeglass.disguises.elements;

import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumSerializer;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;

public class Types {

	static {
		EnumUtils<DisguiseType> disguises = new EnumUtils<>(DisguiseType.class, "disguisetypes");
		Classes.registerClass(new ClassInfo<>(DisguiseType.class, "disguisetype")
				.user("disguise ?types?")
				.name("Disguise Type")
				.defaultExpression(new EventValueExpression<>(DisguiseType.class))
				.usage(disguises.getAllNames())
				.parser(new Parser<DisguiseType>() {
					@Override
					@Nullable
					public DisguiseType parse(String input, ParseContext context) {
						System.out.println("The entity type input was " + input);
						return disguises.parse(input);
					}
					
					@Override
					public String toString(DisguiseType type, int flags) {
						return disguises.toString(type, flags);
					}
					
					@Override
					public String toVariableNameString(DisguiseType type) {
						return toString(type, 0);
					}
				})
				.serializer(new EnumSerializer<>(DisguiseType.class)));
		Classes.registerClass(new ClassInfo<>(FlagWatcher.class, "flagwatcher")
				.user("flag ?watchers?")
				.name("FlagWatcher")
				.defaultExpression(new EventValueExpression<>(FlagWatcher.class))
				.parser(new Parser<FlagWatcher>() {

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}
					
					@Override
					public String toString(FlagWatcher watcher, int flags) {
						return watcher.getCustomName();
					}
					
					@Override
					public String toVariableNameString(FlagWatcher disguise) {
						return toString(disguise, 0);
					}
				}));
		Classes.registerClass(new ClassInfo<>(Disguise.class, "disguise")
				.user("disguises?")
				.name("Disguise")
				.defaultExpression(new EventValueExpression<>(Disguise.class))
				.parser(new Parser<Disguise>() {
					@Override
					@Nullable
					public Disguise parse(String input, ParseContext context) {
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}
					
					@Override
					public String toString(Disguise disguise, int flags) {
						return disguise.getDisguiseName();
					}
					
					@Override
					public String toVariableNameString(Disguise disguise) {
						return toString(disguise, 0);
					}
				}));
	}

}
