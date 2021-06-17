package com.grinderwolf.swm.plugin.config;

import com.grinderwolf.swm.api.world.properties.SlimeProperties;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Difficulty;
import org.bukkit.World;

import static com.grinderwolf.swm.api.world.properties.SlimeProperties.*;

@Data
@AllArgsConstructor
public class WorldData {

    private String dataSource = "file";

    private String spawn = "0, 255, 0";

    private String difficulty = "peaceful";

    private boolean allowMonsters = true;
    private boolean allowAnimals = true;

    private boolean dragonBattle = false;

    private boolean pvp = true;

    private String environment = "NORMAL";
    private String worldType = "DEFAULT";
    private String defaultBiome = "minecraft:plains";

    private boolean loadOnStartup = true;
    private boolean readOnly = false;

    public SlimePropertyMap toPropertyMap() {
        try {
            Enum.valueOf(Difficulty.class, this.difficulty.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("unknown difficulty '" + this.difficulty + "'");
        }

        String[] spawnLocationSplit = spawn.split(", ");

        double spawnX, spawnY, spawnZ;

        try {
            spawnX = Double.parseDouble(spawnLocationSplit[0]);
            spawnY = Double.parseDouble(spawnLocationSplit[1]);
            spawnZ = Double.parseDouble(spawnLocationSplit[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("invalid spawn location '" + this.spawn + "'");
        }

        String environment = this.environment;

        try {
            Enum.valueOf(World.Environment.class, environment.toUpperCase());
        } catch (IllegalArgumentException ex) {
            try {
                int envId = Integer.parseInt(environment);

                if (envId < -1 || envId > 1) {
                    throw new NumberFormatException(environment);
                }

                environment = World.Environment.getEnvironment(envId).name();
            } catch (NumberFormatException ex2) {
                throw new IllegalArgumentException("unknown environment '" + this.environment + "'");
            }
        }

        SlimePropertyMap propertyMap = new SlimePropertyMap();

        propertyMap.setValue(SPAWN_X, (int) spawnX);
        propertyMap.setValue(SPAWN_Y, (int) spawnY);
        propertyMap.setValue(SPAWN_Z, (int) spawnZ);

        propertyMap.setValue(DIFFICULTY, difficulty);
        propertyMap.setValue(ALLOW_MONSTERS, allowMonsters);
        propertyMap.setValue(ALLOW_ANIMALS, allowAnimals);
        propertyMap.setValue(DRAGON_BATTLE, dragonBattle);
        propertyMap.setValue(PVP, pvp);
        propertyMap.setValue(ENVIRONMENT, environment);
        propertyMap.setValue(WORLD_TYPE, worldType);
        propertyMap.setValue(DEFAULT_BIOME, defaultBiome);

        return propertyMap;
    }
}
