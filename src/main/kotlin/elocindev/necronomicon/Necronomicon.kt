package elocindev.necronomicon

//#if FABRIC==1
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
//#if MC>=11904
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
//#else
//$$ import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
//#endif
//#else
//$$ import net.minecraftforge.fml.common.Mod
//$$ import net.minecraftforge.eventbus.api.SubscribeEvent
//$$ import net.minecraftforge.event.RegisterCommandsEvent
//#endif
import net.minecraft.server.command.CommandManager.*
//#if MC>=11904
import net.minecraft.text.Text
//#elseif FABRIC==1
//$$ import net.minecraft.text.TranslatableText
//#else
//$$ import net.minecraft.network.chat.TranslatableComponent
//#endif
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//#if FORGE==1
//$$ @Mod("necronomicon")
//$$ @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
//$$ object Necronomicon {
//#else
object Necronomicon : ModInitializer {
//#endif
    const val NAME = "Necronomicon"
    const val ID = "necronomicon"
    const val VERSION = "1.0.0"
    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger("necronomicon")

    //#if FABRIC==1
    override fun onInitialize() {
        LOGGER.info("Necronomicon Initialized")
    }
    //#else

    //#endif
}
