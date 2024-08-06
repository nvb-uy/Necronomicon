package elocindev.necronomicon.api.text;

import net.minecraft.world.item.ItemStack;

/**
 * @deprecated Use {@link elocindev.necronomicon.api.text.IGradientName} instead.
 */
@Deprecated
public interface IAnimatedText {
    AnimatedText getAnimatedName(ItemStack stack);
}
