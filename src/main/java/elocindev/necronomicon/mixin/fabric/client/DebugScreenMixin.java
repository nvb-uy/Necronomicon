package elocindev.necronomicon.mixin.fabric.client;

import java.util.List;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.necronomicon.CommonInitializer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

//#if FABRIC==1
import net.minecraft.client.gui.hud.DebugHud;
//#endif

//#if FABRIC==1
@Mixin(DebugHud.class)
//#endif
public class DebugScreenMixin {
    //#if FABRIC==1
    @Inject(method = "getLeftText", at = @At("RETURN"))
	protected void getLeftText(CallbackInfoReturnable<List<String>> info) {
        info.getReturnValue().add("Necronomicon API v"+CommonInitializer.VERSION);
	}
    //#endif
}