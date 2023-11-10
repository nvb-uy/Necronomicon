package elocindev.necronomicon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.necronomicon.api.config.v1.NecConfigAPI;

//#if FABRIC==1
import elocindev.necronomicon.example.AnimItemExample;
import net.fabricmc.api.ModInitializer;
//#else
//$$ import net.minecraftforge.fml.common.Mod;
//#endif

//#if FORGE==1
//$$ @Mod(CommonInitializer.MODID)
//$$ public class CommonInitializer {
//#else
public class CommonInitializer implements ModInitializer {
//#endif
    public static final String MODID = "necronomicon";
    public static final Logger LOGGER = LoggerFactory.getLogger("necronomicon");
    public static final String VERSION = "1.3.0";

    public static final boolean ENABLE_EXAMPLES = true;

    //#if FABRIC==1
    @Override
    public void onInitialize() {
        LOGGER.info("Necronomicon Initialized");
        init();

        if (ENABLE_EXAMPLES) {
            AnimItemExample.init();
        }
    }
    //#endif

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