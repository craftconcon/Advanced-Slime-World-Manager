package com.grinderwolf.swm.api.world.properties.types;

import com.flowpowered.nbt.IntTag;
import com.grinderwolf.swm.api.world.properties.PropertyType;

import java.util.function.Function;

public class IntSlimeProperty extends SlimeProperty<Integer> {
    public IntSlimeProperty(String nbtName, Integer defaultValue) {
        this(nbtName, defaultValue, null);
    }

    public IntSlimeProperty(String nbtName, Integer defaultValue, Function<Integer, Boolean> validator) {
        super(nbtName, PropertyType.INT, defaultValue, validator,
                (value, parent, self) -> parent.put(self.getNbtName(), new IntTag(self.getNbtName(), value)),
                (parent, self) -> parent.getIntValue(self.getNbtName()).orElse(self.getDefaultValue())
        );
    }
}
