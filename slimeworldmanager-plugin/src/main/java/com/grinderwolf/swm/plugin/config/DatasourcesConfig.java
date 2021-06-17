package com.grinderwolf.swm.plugin.config;

import io.github.portlek.configs.snakeyaml.bukkit.BukkitSnakeyaml;
import io.github.portlek.transformer.TransformedObject;
import io.github.portlek.transformer.TransformerPool;
import io.github.portlek.transformer.annotations.CustomKey;
import io.lettuce.core.RedisURI;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class DatasourcesConfig extends TransformedObject{

    public static void loadConfig(final Plugin plugin) {
        TransformerPool.create(new DatasourcesConfig())
          .withFile(new File(plugin.getDataFolder(), "sources.yml"))
          .withResolver(new BukkitSnakeyaml())
          .initiate();
    }

    @CustomKey("file") public static FileConfig fileConfig = new FileConfig();
    @CustomKey("mysql") public static MysqlConfig mysqlConfig = new MysqlConfig();
    @CustomKey("mongodb") public static MongoDBConfig mongoDbConfig = new MongoDBConfig();
    @CustomKey("redis") public static RedisConfig redisConfig = new RedisConfig();

    public static class MysqlConfig extends TransformedObject{

        public static boolean enabled = false;

       public static String host = "127.0.0.1";
        public static int port = 3306;

      public static String username = "slimeworldmanager";
         public static String password = "";

        public static String database = "slimeworldmanager";

        public static boolean usessl = false;

         public static String sqlUrl = "jdbc:mysql://{host}:{port}/{database}?autoReconnect=true&allowMultiQueries=true&useSSL={usessl}";
    }

    public static class MongoDBConfig extends TransformedObject{

        public static boolean enabled = false;
public static String host = "127.0.0.1";
      public static int port = 27017;

        @CustomKey("auth") public static String authSource = "admin";
     public static String username = "slimeworldmanager";
       public static String password = "";

        public static String database = "slimeworldmanager";
      public static String collection = "worlds";

      public static String uri = "";
    }

    public static class FileConfig extends TransformedObject {

       public static String path = "slime_worlds";

    }

  public static class RedisConfig extends TransformedObject{

    public static boolean enabled = false;
  public static String uri = "redis://127.0.0.1/";
  }
}
