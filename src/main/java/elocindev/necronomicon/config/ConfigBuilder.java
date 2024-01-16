package elocindev.necronomicon.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import elocindev.necronomicon.api.json.JsonFileAPI;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

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

                processNestedConfigs(defaultConfig, configClass);

                String defaultJson = BUILDER.toJson(defaultConfig);
                StringBuffer buffer = new StringBuffer(defaultJson);

                for (Field field : configClass.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Comments.class)) {
                        for (Comment comment : field.getAnnotation(Comments.class).value()) {
                            insertComment(buffer, field.getName(), comment.value());
                        }
                    } else if (field.isAnnotationPresent(Comment.class)) {
                        String comment = field.getAnnotation(Comment.class).value();
                        insertComment(buffer, field.getName(), comment);
                    }
                }

                Files.writeString(file, buffer.toString());
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

                String withoutComments = preprocessJson(json);

                T configEntries = BUILDER.fromJson(withoutComments, configClass);
                processNestedConfigs(configEntries, configClass);
                
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
            
                JsonFileAPI.setPrettyPrint(file);

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

    // Utility methods

    private static void insertComment(StringBuffer jsonBuffer, String fieldName, String comment) {
        String searchString = "\"" + fieldName + "\":";
        int fieldIndex = jsonBuffer.indexOf(searchString);

        if (fieldIndex == -1) {
            return;
        }

        int lineStartIndex = jsonBuffer.lastIndexOf("\n", fieldIndex) + 1;

        String commentString = "  // " + comment + "\n";

        jsonBuffer.insert(lineStartIndex, commentString);
    }

    private static String preprocessJson(String json) {
        StringJoiner sj = new StringJoiner("\n");
        String[] lines = json.split("\n");

        for (String line : lines) {
            String trimmedLine = line.trim();
            if (!trimmedLine.startsWith("//")) {
                sj.add(line);
            }
        }

        return sj.toString();
    }

    private static <T> void processNestedConfigs(T configInstance, Class<?> configClass) {
        for (Field field : configClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(NestedConfig.class)) {
                field.setAccessible(true);
                try {
                    Object nestedConfig = field.get(configInstance);
                    if (nestedConfig == null) {
                        nestedConfig = field.getType().getDeclaredConstructor().newInstance();
                        field.set(configInstance, nestedConfig);
                    }
                    processNestedConfigs(nestedConfig, field.getType());
                } catch (Exception e) {
                    throw new RuntimeException("Failed to process nested config field: " + field.getName(), e);
                }
            }
        }
    }
}