package elocindev.necronomicon.mixin.common.item;

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

*///? } else {
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.necronomicon.api.text.IAnimatedText;
import elocindev.necronomicon.api.text.IGradientName;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

@Mixin(ItemStack.class)
public abstract class AnimatedItemNameMixin {

@SuppressWarnings("deprecation")
    @Inject(method="getHoverName", at = @At(value = "HEAD"), cancellable = true)
    private void getName(CallbackInfoReturnable<Component> cir) {
        ItemStack stack = (ItemStack) (Object) this;
       
        Component name = stack.get(DataComponents.ITEM_NAME);
        Component customName = stack.get(DataComponents.CUSTOM_NAME);

        if(!(stack.getItem() instanceof IGradientName gradient)) return; 
        
        if (customName == null) {
            try {
                MutableComponent itemName = gradient.getAnimatedName(stack).getText(customName);

                cir.setReturnValue(itemName);
                return;
                
            } catch (Exception e) {
                stack.remove(DataComponents.CUSTOM_NAME);
            }
        }

        MutableComponent itemName = dynamicItemName.getAnimatedName(stack).getText(stack.getItem().getName(stack));
    }
}
//? }
