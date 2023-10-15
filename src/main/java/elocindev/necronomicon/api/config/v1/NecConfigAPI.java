package elocindev.necronomicon.api.config.v1;

import elocindev.necronomicon.config.ConfigBuilder;
//#if FABRIC==1
import net.fabricmc.loader.api.FabricLoader;
//#else
//$$ import net.minecraftforge.fml.loading.FMLPaths;
//#endif

public class NecConfigAPI {
    /**
     * 
     * @param <T>           Generic config class with a [String]getFile() method.
     * @param configClass   The config class to initialize its contents. 
     */
    public static <T> void registerConfig(Class<T> configClass) {
        ConfigBuilder.initializeConfigs(configClass);
    }

    public static String getFile(String file) {
        //#if FABRIC==1
        return FabricLoader.getInstance().getConfigDir().resolve(file).toString();
        //#else
        //$$ return FMLPaths.GAMEDIR.get().toAbsolutePath().resolve("config").resolve(file).toString();
        //#endif
    }
}
