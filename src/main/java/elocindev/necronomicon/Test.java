package elocindev.necronomicon;

import elocindev.necronomicon.config.ConfigBuilder;


public class Test {
    public static void loadConfig() {
        for (String a : NecronomiconConfig.INSTANCE.list) {
            System.out.println(a);
        }

        ConfigBuilder.initializeConfigs(NecronomiconConfig.class);
    }
}
