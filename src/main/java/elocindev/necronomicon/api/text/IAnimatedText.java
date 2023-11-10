package elocindev.necronomicon.api.text;

//#if FABRIC==1
import net.minecraft.item.ItemStack;
//#else
//$$ import net.minecraft.world.item.ItemStack;
//#endif

public interface IAnimatedText {
    AnimatedText getAnimatedName(ItemStack stack);
}
