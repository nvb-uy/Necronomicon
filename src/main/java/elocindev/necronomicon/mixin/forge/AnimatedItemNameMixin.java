package elocindev.necronomicon.mixin.forge;

//#if FORGE==1

//$$ import net.minecraft.world.item.ItemStack;

//$$ import net.minecraft.nbt.CompoundTag;
//$$ import net.minecraft.network.chat.Component;
//$$ import net.minecraft.network.chat.MutableComponent;

//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//$$ import elocindev.necronomicon.api.text.IAnimatedText;

// Credits to the awesome boykisser RXJpaw !

//$$ @Mixin(ItemStack.class)
//$$ public abstract class AnimatedItemNameMixin {
//$$     @Inject(method="getHoverName", at = @At(value = "HEAD"), cancellable = true)
//$$     private void getName(CallbackInfoReturnable<Component> cir) {
//$$         ItemStack stack = (ItemStack)(Object)this;

//$$         if((Object) stack.getItem() instanceof IAnimatedText dynamicItemName) {
//$$             CompoundTag nbtCompound = stack.getTagElement("display");

//$$             if (nbtCompound != null && nbtCompound.contains("Name", 8)) {
//$$                 try {
//$$                     Component text = Component.Serializer.fromJson(nbtCompound.getString("Name"));
//$$                     if (text != null) {
//$$                         MutableComponent itemName = dynamicItemName.getAnimatedName(stack).getText(text);
//$$                         cir.setReturnValue(itemName);
//$$                         return;
//$$                     }

//$$                     nbtCompound.remove("Name");
//$$                 } catch (Exception e) {
//$$                     nbtCompound.remove("Name");
//$$                 }
//$$             }

//$$             MutableComponent itemName = dynamicItemName.getAnimatedName(stack).getText(stack.getItem().getName(stack));
//$$             cir.setReturnValue(itemName);
//$$         }
//$$     }
//$$ }
//#endif