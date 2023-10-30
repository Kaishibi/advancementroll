package me.falu.advancementroll.mixin.features.noTransformFeedback;

import net.minecraft.client.render.entity.ZombieVillagerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieVillagerEntityRenderer.class)
public class ZombieVillagerEntityRendererMixin {
    @Inject(method = "isShaking*", at = @At("RETURN"), cancellable = true)
    private void removeShaking(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
