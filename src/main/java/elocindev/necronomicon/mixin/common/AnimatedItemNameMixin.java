package elocindev.necronomicon.mixin.common;

//? if <=1.20.1 {

/*import org.spongepowered.asm.mixin.Mixin;
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

*///?}