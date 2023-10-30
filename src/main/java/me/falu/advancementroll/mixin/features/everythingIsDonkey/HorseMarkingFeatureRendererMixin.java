package me.falu.advancementroll.mixin.features.everythingIsDonkey;

import net.minecraft.client.render.entity.feature.HorseMarkingFeatureRenderer;
import net.minecraft.entity.passive.HorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HorseMarkingFeatureRenderer.class)
public class HorseMarkingFeatureRendererMixin {
    @Redirect(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/HorseEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/passive/HorseEntity;isInvisible()Z"
            )
    )
    private boolean noMarking(HorseEntity instance) { return true; }
}
