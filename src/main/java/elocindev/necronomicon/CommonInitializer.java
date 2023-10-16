package elocindev.necronomicon;

import elocindev.necronomicon.api.config.v1.NecConfigAPI;

public class CommonInitializer {
    public static final String VERSION = "1.0.9";

    /**
     * An example on how to register your custom config class.
     * Platform doesn't matter as that logic is handled by the API.
     * 
     * This should be called in your mod's constructor.
     * 
     * @platform Both
     * 
     * @since 1.0.5
     * @author ElocinDev
     */
    public static void init() {
        NecConfigAPI.registerConfig(NecronomiconConfig.class);
    }
}