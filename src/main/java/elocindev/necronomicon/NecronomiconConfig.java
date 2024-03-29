package elocindev.necronomicon;

import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;
import elocindev.necronomicon.config.NestedConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

import java.util.List;

/**
 * This is an example config class. Must be initialized using {@link NecConfigAPI#registerConfig(Class)}.
 * 
 * @apiNote     The instance of this class needs the {@link NecConfig} annotation.
 * @apiNote     All fields from this class will be registered as config entries.
 * 
 * @see         NecConfigAPI
 * @platform    Both
 * 
 * @since       1.0.5
 * @author      ElocinDev
 */
public class NecronomiconConfig {
    /**
     * The instance of this config class.
     * 
     * Must be implemented with the annotation or the config will not load properly.
     * Must be static, holds the values read from the file.
     */
    @NecConfig
    public static NecronomiconConfig INSTANCE;

    /**
     * NecConfig will call the getFile() method to get the path of the config as string.
     * 
     * @apiNote Must not have any parameters and must return a path as String, see {@link elocindev.necronomicon.api.config.v1.NecConfigAPI#getFile(String) NecConfigAPI#getFile}.
     * 
     * @return  String : The instance of this class.
     */
    public static String getFile() {
        return NecConfigAPI.getFile("necronomicon.json5");
    }

    /**
     * The entries.
     * 
     * All fields from this class will be registered as config entries, they must be public, non-static and serializable.
     * The values defined in each field will represent the default config values in the file generated.
     * 
    **/


    /**
     * Adds a comment above a field. Requires getFile() to return a json5 file for readability purposes.
     * The file will still get parsed the same way no matter the extension, but most text editors / IDEs won't support comments with json, so use json5 or jsonc.
     * 
     * You can repeat this annotation to add multiple comments to a field.
     *
     * @since 1.4.0
     * @author ElocinDev
     */
    @Comment("This is used to debug stuff")
    public boolean debug = false;

    @Comment("A test boolean")
    public boolean test = false;

    @Comment("A list!")
    @Comment("Supports multiple values using List.of()")
    public List<String> list = List.of("test", "test2");
    
    /**
     * Defines a nested config on a dynamic class initializer.
     * The default values of the config are defined in the class that's being initialized.
     * 
     * Access it through the INSTANCE of the config (@NecConfig).
     * 
     * @since 1.4.0
     * @author ElocinDev
     */
    @NestedConfig
    public ExampleColorConfig exampleColors = new ExampleColorConfig();

    public static class ExampleColorConfig {
        public int red = 255;
        public int green = 255;
        public int blue = 255;
    }
}



