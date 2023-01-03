package me.wurgo.advancementroll.mixin.features.noGatewayBeam;

import net.minecraft.block.entity.EndGatewayBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndGatewayBlockEntity.class)
public class EndGatewayBlockEntityMixin {
    @Inject(method = "getRecentlyGeneratedBeamHeight", at = @At("RETURN"), cancellable = true)
    private void noBeam(float tickDelta, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(0.0f);
    }
}
