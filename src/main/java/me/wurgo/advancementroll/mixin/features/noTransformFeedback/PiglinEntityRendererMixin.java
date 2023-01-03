package me.wurgo.advancementroll.mixin.features.noTransformFeedback;

import net.minecraft.client.render.entity.PiglinEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinEntityRenderer.class)
public class PiglinEntityRendererMixin {
    @Inject(method = "isShaking*", at = @At("RETURN"), cancellable = true)
    private void removeShaking(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
