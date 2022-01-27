package me.limeglass.disguises.elements;

import org.bukkit.entity.Entity;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;

public class DefaultConverters {

	static {
		Converters.registerConverter(Entity.class, Disguise.class, new Converter<Entity, Disguise>() {
			@Override
			@Nullable
			public Disguise convert(Entity entity) {
				return DisguiseAPI.constructDisguise(entity, true, true);
			}
		});
		Converters.registerConverter(Entity.class, DisguiseType.class, new Converter<Entity, DisguiseType>() {
			@Override
			@Nullable
			public DisguiseType convert(Entity entity) {
				return DisguiseType.getType(entity);
			}
		});
		Converters.registerConverter(Disguise.class, FlagWatcher.class, new Converter<Disguise, FlagWatcher>() {
			@Override
			@Nullable
			public FlagWatcher convert(Disguise disguise) {
				return disguise.getWatcher();
			}
		});
		Converters.registerConverter(Disguise.class, Entity.class, new Converter<Disguise, Entity>() {
			@Override
			@Nullable
			public Entity convert(Disguise disguise) {
				return disguise.getEntity();
			}
		});Converters.registerConverter(Disguise.class, FlagWatcher.class, new Converter<Disguise, FlagWatcher>() {
			@Override
			@Nullable
			public FlagWatcher convert(Disguise disguise) {
				return disguise.getWatcher();
			}
		});
	}
}
