package com.grinderwolf.swm.plugin.config;

import com.grinderwolf.swm.plugin.log.Logging;
import io.leangen.geantyref.TypeToken;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.paper.transformer.resolvers.BukkitSnakeyaml;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.infumialib.transformer.TransformedData;
import tr.com.infumia.infumialib.transformer.TransformedObject;
import tr.com.infumia.infumialib.transformer.TransformerPool;
import tr.com.infumia.infumialib.transformer.declarations.GenericDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WorldsConfig extends TransformedObject {

    public static Map<String, WorldData> worlds = new HashMap<>();

    public static void loadConfig(final Plugin plugin) {
        TransformerPool.create(new WorldsConfig())
          .withFile(new File(plugin.getDataFolder(), "worlds.yml"))
          .withResolver(new BukkitSnakeyaml())
          .withTransformPack(registry -> registry
            .withSerializers(new WorldDataSerializer()))
          .initiate();
    }

    private static final class WorldDataSerializer implements ObjectSerializer<WorldData> {
        @NotNull
        @Override
        public  Optional<WorldData> deserialize(@NotNull TransformedData data, @Nullable GenericDeclaration genericDeclaration) {
            WorldData worldData = new WorldData(
            data.get("source", String.class).orElse("file"),
            data.get("spawn",  String.class).orElse("0, 255, 0"),
            data.get("difficulty", String.class).orElse("peaceful"),
            data.get("allowMonsters", boolean.class).orElse(true),
            data.get("allowAnimals",  boolean.class).orElse(true),
            data.get("dragonBattle",  boolean.class).orElse(false),
            data.get("pvp", boolean.class).orElse(true),
            data.get("environment",  String.class).orElse("NORMAL"),
            data.get("worldType",  String.class).orElse("DEFAULT"),
            data.get("defaultBiome",  String.class).orElse("minecraft:plains"),
            data.get("loadOnStartup",  boolean.class).orElse(true),
            data.get("readOnly",  boolean.class).orElse(false)
            );
            return Optional.of(worldData);
        }
        @NotNull
        @Override
        public  Optional<WorldData> deserialize(@NotNull WorldData worldData, @NotNull TransformedData transformedData, @Nullable GenericDeclaration genericDeclaration) {
            return deserialize(transformedData, genericDeclaration);
        }

        @Override
        public void serialize(@NotNull WorldData worldData, @NotNull TransformedData data) {
            data.add("source", worldData.getDataSource(), String.class);
            data.add("spawn", worldData.getSpawn(), String.class);
            data.add("difficulty", worldData.getDifficulty(), String.class);
            data.add("allowMonsters", worldData.isAllowMonsters(), boolean.class);
            data.add("allowAnimals", worldData.isAllowAnimals(), boolean.class);
            data.add("dragonBattle", worldData.isDragonBattle(), boolean.class);
            data.add("pvp", worldData.isPvp(), boolean.class);
            data.add("environment", worldData.getEnvironment(), String.class);
            data.add("worldType", worldData.getWorldType(), String.class);
            data.add("defaultBiome", worldData.getDefaultBiome(), String.class);
            data.add("loadOnStartup", worldData.isLoadOnStartup(), boolean.class);
            data.add("readOnly", worldData.isReadOnly(), boolean.class);
        }

        @Override
        public boolean supports(@NotNull Class<?> aClass) {
            return aClass == WorldData.class;
        }
    }
}
