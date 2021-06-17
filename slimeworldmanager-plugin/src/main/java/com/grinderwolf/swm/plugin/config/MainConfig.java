package com.grinderwolf.swm.plugin.config;

import com.grinderwolf.swm.plugin.log.Logging;
import io.github.portlek.configs.snakeyaml.bukkit.BukkitSnakeyaml;
import io.github.portlek.transformer.TransformedObject;
import io.github.portlek.transformer.TransformerPool;
import io.github.portlek.transformer.annotations.CustomKey;
import io.leangen.geantyref.TypeToken;
import lombok.Data;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MainConfig extends TransformedObject {

  public static UpdateOptions updater = new UpdateOptions();
  public static final class UpdateOptions extends TransformedObject {

    public static boolean enabled = true;

    @CustomKey(value = "onjoinmessage")
    public static boolean messageEnabled = true;
  }
    public static void loadConfig(Plugin plugin) {
      TransformerPool.create(new MainConfig())
        .withFile(new File(plugin.getDataFolder(), "main.yml"))
        .withResolver(new BukkitSnakeyaml())
        .initiate();
    }
}
