package com.grinderwolf.swm.api.world.properties;

import com.grinderwolf.swm.api.world.properties.types.BooleanSlimeProperty;
import com.grinderwolf.swm.api.world.properties.types.IntSlimeProperty;
import com.grinderwolf.swm.api.world.properties.types.SlimeProperty;
import com.grinderwolf.swm.api.world.properties.types.StringSlimeProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class with all existing properties.
 */
public class SlimeProperties {

    public static final SlimeProperty<Integer> SPAWN_X = new IntSlimeProperty("spawnX", 0);
    public static final SlimeProperty<Integer> SPAWN_Y = new IntSlimeProperty("spawnY", 255);
    public static final SlimeProperty<Integer> SPAWN_Z = new IntSlimeProperty("spawnZ", 0);

    public static final SlimeProperty<String> DIFFICULTY = new StringSlimeProperty("difficulty", "peaceful",
            (value) ->
                    value.equalsIgnoreCase("peaceful") || value.equalsIgnoreCase("easy")
                            || value.equalsIgnoreCase("normal") || value.equalsIgnoreCase("hard")
    );

    public static final SlimeProperty<Boolean> ALLOW_MONSTERS = new BooleanSlimeProperty("allowMonsters", true);
    public static final SlimeProperty<Boolean> ALLOW_ANIMALS = new BooleanSlimeProperty("allowAnimals", true);

    public static final SlimeProperty<Boolean> PVP = new BooleanSlimeProperty("pvp", true);

    public static final SlimeProperty<String> ENVIRONMENT = new StringSlimeProperty("environment", "normal",
            (value) ->
                    value.equalsIgnoreCase("normal") || value.equalsIgnoreCase("nether") || value.equalsIgnoreCase("the_end")
    );

    public static final SlimeProperty<String> WORLD_TYPE = new StringSlimeProperty("worldtype", "default",
            (value) ->
                    value.equalsIgnoreCase("default") || value.equalsIgnoreCase("flat") || value.equalsIgnoreCase("large_biomes")
                            || value.equalsIgnoreCase("amplified") || value.equalsIgnoreCase("customized")
                            || value.equalsIgnoreCase("debug_all_block_states") || value.equalsIgnoreCase("default_1_1")
    );

    public static final List<SlimeProperty<?>> VALUES = new ArrayList<>(Arrays.asList(SPAWN_X, SPAWN_Y, SPAWN_Z, DIFFICULTY, ALLOW_MONSTERS, ALLOW_ANIMALS, PVP, ENVIRONMENT, WORLD_TYPE));
}
