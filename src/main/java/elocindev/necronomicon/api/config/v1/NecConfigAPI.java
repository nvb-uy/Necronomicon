package elocindev.necronomicon.api.config.v1;

import elocindev.necronomicon.config.ConfigBuilder;
//#if FABRIC==1
import net.fabricmc.loader.api.FabricLoader;
//#else
//$$ import net.minecraftforge.fml.loading.FMLPaths;
//#endif

/**
 * Necronomicon's Config API. A simple way of doing JSON configs that supports both Forge and Fabric.
 * @author ElocinDev
 * 
 * @apiNote Create your own config class, see example: {@link elocindev.necronomicon.NecronomiconConfig NecronomiconConfig}.
 * @apiNote To initialize your config use {@link elocindev.necronomicon.api.config.v1.NecConfigAPI#registerConfig(Class) NecConfigAPI#registerConfig}
 * 
 * @since 1.0.5
 */
public class NecConfigAPI {
    /** 
     *  Registers a config class to be initialized.
     * 
     * @see {@link elocindev.necronomicon.NecronomiconConfig Example Config Class}
     * 
     * @param <T>           Generic config class with a [String]getFile() method.
     * @param configClass   The config class to initialize its contents.
     * 
     * @platform    Both
     * 
     * @since 1.0.5
     * @author ElocinDev
     */
    public static <T> void registerConfig(Class<T> configClass) {
        ConfigBuilder.initializeConfigs(configClass);
    }

    /**
     * Gets the file path for the config file.
     * 
     * @platform        Both
     * 
     * @param file      The file name including extension. (Ex. "necronomicon.json")
     * @return          String : The file path for the config file to be returned in your config class getFile method.
     * 
     * @since 1.0.5
     * @author ElocinDev
     */
    public static String getFile(String file) {
        //#if FABRIC==1
        return FabricLoader.getInstance().getConfigDir().resolve(file).toString();
        //#else
        //$$ return FMLPaths.GAMEDIR.get().toAbsolutePath().resolve("config").resolve(file).toString();
        //#endif
    }
}
