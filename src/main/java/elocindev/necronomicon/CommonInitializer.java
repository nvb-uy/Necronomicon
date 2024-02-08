package elocindev.necronomicon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.necronomicon.api.NecUtilsAPI;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

//#if FABRIC==1
import net.fabricmc.api.ModInitializer;
//#else
//$$ import net.minecraftforge.fml.common.Mod;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//$$ import net.minecraftforge.eventbus.api.SubscribeEvent;
//#endif

//#if FORGE==1
//$$ @Mod.EventBusSubscriber(modid = CommonInitializer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
//$$ @Mod(CommonInitializer.MODID)
//$$ public class CommonInitializer {
//#else
public class CommonInitializer implements ModInitializer {
//#endif
    public static final String MODID = "necronomicon";
    public static final Logger LOGGER = LoggerFactory.getLogger("necronomicon");
    public static final String VERSION = "1.4.2";

    public static final boolean ENABLE_EXAMPLES = false;

    //#if FABRIC==1
    @Override
    public void onInitialize() {
        LOGGER.info("Necronomicon Initialized");
        init();
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
        
        if (NecUtilsAPI.isModLoaded("embeddium")) {
            String yes = "there is a brick about to fall through your roof at terminal velocity";

            LOGGER.info(yes);

            if (NecUtilsAPI.isModLoaded("quark")) {
                LOGGER.info(yes);
            }
        }
    }

    //#if FORGE==1
    //$$ @SubscribeEvent
    //$$ public void onCommonSetup(FMLCommonSetupEvent event) {
    //$$     LOGGER.info("Necronomicon Initialized");
    //$$     init();
    //$$ }
    //#endif
}