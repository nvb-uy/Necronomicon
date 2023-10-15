package elocindev.necronomicon.client;

//#if FABRIC==0
//$$ import elocindev.necronomicon.CommonInitializer;

//$$ import net.minecraftforge.fml.common.Mod;
//$$ import net.minecraftforge.eventbus.api.SubscribeEvent;

//$$ import net.minecraftforge.client.event.CustomizeGuiOverlayEvent.DebugText;

//$$ @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
//#endif
public class GuiOverlayEvent {    
    //#if FABRIC==0
    //$$ @SubscribeEvent
    //$$ public static void modifyDebugScreen(DebugText event) {
    //$$    event.getLeft().add("Necronomicon API v"+CommonInitializer.VERSION);
    //$$ }
    //#endif
}
