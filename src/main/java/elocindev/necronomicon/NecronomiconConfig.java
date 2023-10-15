package elocindev.necronomicon;

import elocindev.necronomicon.config.NecConfig;

import java.util.List;

import elocindev.necronomicon.api.config.v1.NecConfigAPI;

/**
 * This is an example config class. Must be initialized using {@link NecConfigAPI#registerConfig(Class)}.
 * 
 * @apiNote The instance of this class needs the {@link NecConfig} annotation.
 * @apiNote All fields from this class will be registered as config entries.
 * 
 * @see NecConfigAPI
 * 
 * @since 1.0.5
 * @author ElocinDev
 */
public class NecronomiconConfig {
    @NecConfig
    public static NecronomiconConfig INSTANCE;

    public static String getFile() {
        return NecConfigAPI.getFile("necronomicon.json");
    }

    public boolean debug = false;
    public boolean test = false;
    public List<String> list = List.of("test", "test2");
}