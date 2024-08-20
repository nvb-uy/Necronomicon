package elocindev.necronomicon.mixin.common.item;

//? if <=1.20.1 {

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.necronomicon.api.text.IAnimatedText;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemStack.class)
public abstract class AnimatedItemNameMixin {
    @SuppressWarnings("deprecation")
    @Inject(method="getHoverName", at = @At(value = "HEAD"), cancellable = true)
    private void getName(CallbackInfoReturnable<Component> cir) {
        ItemStack stack = (ItemStack) (Object) this;

        if((Object) stack.getItem() instanceof IAnimatedText dynamicItemName) {
            CompoundTag nbtCompound = stack.getTagElement("display");

            if (nbtCompound != null && nbtCompound.contains("Name", 8)) {
                try {
                    Component text = Component.Serializer.fromJson(nbtCompound.getString("Name"));
                    
                    if (text != null) {
                        MutableComponent itemName = dynamicItemName.getAnimatedName(stack).getText(text);

                        cir.setReturnValue(itemName);
                        return;
                    }

                    nbtCompound.remove("Name");
                } catch (Exception e) {
                    nbtCompound.remove("Name");
                }
            }

            MutableComponent itemName = dynamicItemName.getAnimatedName(stack).getText(stack.getItem().getName(stack));
            cir.setReturnValue(itemName);
        }
    }
}

//?} else {
/*import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.necronomicon.api.text.IAnimatedGradientName;
import elocindev.necronomicon.api.text.IGradientName;
import elocindev.necronomicon.api.text.TextAPI;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemStack.class)
public abstract class AnimatedItemNameMixin {

    @Inject(method="getHoverName", at = @At(value = "HEAD"), cancellable = true)
    private void getName(CallbackInfoReturnable<Component> cir) {
        ItemStack stack = (ItemStack) (Object) this;
       
        Component name = stack.get(DataComponents.ITEM_NAME);
        Component customName = stack.get(DataComponents.CUSTOM_NAME);

        if (stack.getItem() instanceof IGradientName gradient) {
            if (customName == null) {
                try {
                    int[] colors = gradient.getGradientColors();

                    MutableComponent itemName = TextAPI.Styles.getStaticGradient(name, colors[0], colors[1]);

                    cir.setReturnValue(itemName);
                    return;
                    
                } catch (Exception e) {
                    stack.remove(DataComponents.CUSTOM_NAME);
                }
            } else if (gradient.applyOnCustomName()) {
                try {
                    int[] colors = gradient.getGradientColors();

                    MutableComponent itemName = TextAPI.Styles.getStaticGradient(customName, colors[0], colors[1]);

                    cir.setReturnValue(itemName);
                    return;
                    
                } catch (Exception e) {
                    stack.remove(DataComponents.CUSTOM_NAME);
                }
            }
        } else if (stack.getItem() instanceof IAnimatedGradientName animatedGradient) {
            if (customName == null) {
                try {
                    int[] colors = animatedGradient.getGradientColors();

                    MutableComponent itemName = TextAPI.Styles.getGradient(name, 1, colors[0], colors[1], animatedGradient.getAnimatedNameSpeed());

                    if (animatedGradient.isRainbowName()) {
                        itemName = TextAPI.Styles.getRainbowGradient(name, 1, animatedGradient.getAnimatedNameSpeed());
                    }

                    cir.setReturnValue(itemName);
                    return;
                    
                } catch (Exception e) {
                    stack.remove(DataComponents.CUSTOM_NAME);
                }
            } else if (animatedGradient.applyOnCustomName()) {
                try {
                    int[] colors = animatedGradient.getGradientColors();

                    MutableComponent itemName = TextAPI.Styles.getGradient(customName, 1, colors[0], colors[1], animatedGradient.getAnimatedNameSpeed());

                    if (animatedGradient.isRainbowName()) {
                        itemName = TextAPI.Styles.getRainbowGradient(customName, 1, animatedGradient.getAnimatedNameSpeed());
                    }

                    cir.setReturnValue(itemName);
                    return;
                    
                } catch (Exception e) {
                    stack.remove(DataComponents.CUSTOM_NAME);
                }
            }
        }
    }
}
*///?}
