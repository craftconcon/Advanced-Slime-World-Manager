package com.grinderwolf.swm.api.world.properties.types;

import com.flowpowered.nbt.ByteTag;
import com.grinderwolf.swm.api.world.properties.PropertyType;

import java.util.function.Function;

public class BooleanSlimeProperty extends SlimeProperty<Boolean> {
    public BooleanSlimeProperty(String nbtName, Boolean defaultValue) {
        this(nbtName, defaultValue, null);
    }

    public BooleanSlimeProperty(String nbtName, Boolean defaultValue, Function<Boolean, Boolean> validator) {
        super(nbtName, PropertyType.BOOLEAN, defaultValue, validator,
                (value, parent, self) -> parent.put(self.getNbtName(), new ByteTag(self.getNbtName(), (byte) (value ? 1 : 0))),
                (parent, self) -> parent.getByteValue(self.getNbtName()).map((value) -> value == 1).orElse(self.getDefaultValue())
        );
    }
}
