package elocindev.necronomicon.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger("necronomicon");
    private static final Gson BUILDER = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T registerConfig(Path file, Class<T> configClass) {
        try {
            if (Files.notExists(file)) {
                T defaultConfig;
                try {
                    LOGGER.info("Creating default config file for " + configClass.getName() + ".");
                    defaultConfig = configClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create an instance of the config class.", e);
                }
    
                String defaultJson = BUILDER.toJson(defaultConfig);
                Files.writeString(file, defaultJson);
                return defaultConfig;
            }  else {
                String json;
                try {
                    LOGGER.info("File exists, reading config for " + configClass.getName() + ".");
                    json = Files.readString(file);
                    LOGGER.info("Config for " + configClass.getName() + " read successfully.");
            
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read the config file " + file.getFileName().toString(), e);
                }
            
                T configEntries = BUILDER.fromJson(json, configClass);
            
                String updatedJson = BUILDER.toJson(configEntries);
            
                JsonObject updatedJsonObject = BUILDER.fromJson(updatedJson, JsonObject.class);
                JsonObject originalJsonObject = BUILDER.fromJson(json, JsonObject.class);
            
                for (String key : originalJsonObject.keySet()) {
                    if (!updatedJsonObject.has(key)) {
                        originalJsonObject.remove(key);
                    }
                }

                try {
                    Files.writeString(file, originalJsonObject.toString());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to write the updated config to file.", e);
                }
            
                return configEntries;
            }
        } catch (Exception e) {
            throw new RuntimeException(file.getFileName().toString()+" - Failed to register config, it is likely that you added invalid entries in your config, or that the formatting isn't valid.", e);
        }
    }    

    @SuppressWarnings("unchecked")
    public static <T> void initializeConfigs(Class<T> configClass) {
        Field[] fields = configClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NecConfig.class)) {
                try {
                    if (field.getType() == configClass) {
                        field.setAccessible(true);
                        String filePath = (String) field.getType().getMethod("getFile").invoke(null);
                        Path file = Paths.get(filePath);

                        Object configInstance = registerConfig(file, (Class<Object>) field.getType());

                        field.set(field, configInstance);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to initialize config field: " + field.getName(), e);
                }
            }
        }
    }
}
