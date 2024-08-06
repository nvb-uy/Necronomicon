package elocindev.necronomicon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elocindev.necronomicon.api.NecUtilsAPI;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

//? if forge {
/*

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

*/
//?} elif neoforge {
/*

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

*/
//? }


//? if (forge || neoforge) {
/*

@Mod.EventBusSubscriber(modid = CommonInitializer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(CommonInitializer.MODID)
public class CommonInitializer {

*/
//?} elif fabric {
import net.fabricmc.api.ModInitializer;

public class CommonInitializer implements ModInitializer {
//? }

    public static final String MODID = "necronomicon";
    public static final Logger LOGGER = LoggerFactory.getLogger("necronomicon");
    public static final String VERSION = "1.5.0";

    public static final boolean ENABLE_EXAMPLES = false;

    //? if fabric {
    @Override
    public void onInitialize() {
        LOGGER.info("Necronomicon Initialized");
        init();
    }
    //? }

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

    //? if forge || neoforge {
    /*
    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Necronomicon Initialized");
        init();
    }
    */
    //? }
}