package me.wurgo.advancementroll.mixin.features.neverSwitchWitherPhase;

import net.minecraft.entity.boss.WitherEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherEntity.class)
public class WitherEntityMixin {
    @Inject(method = "shouldRenderOverlay", at = @At("RETURN"), cancellable = true)
    private void neverSwitchPhase(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
