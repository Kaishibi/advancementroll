package me.wurgo.advancementroll.mixin.features.shrinkingDragonFireball;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {
    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/AreaEffectCloudEntity;setDuration(I)V"))
    private void makeDurationShorter(AreaEffectCloudEntity instance, int duration) {
        instance.setDuration(10);
        instance.setWaitTime(0);
    }

    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/AreaEffectCloudEntity;setRadiusGrowth(F)V"))
    private void shrinkRadius(AreaEffectCloudEntity instance, float growth) {
        instance.setRadiusGrowth(-instance.getRadius() / (float)instance.getDuration());
    }
}
