package elocindev.necronomicon.api.text;

import net.minecraft.item.ItemStack;
//#if FABRIC==1
public interface IAnimatedText {
    AnimatedText getAnimatedName(ItemStack stack);
}
//#endif
