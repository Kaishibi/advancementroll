package me.falu.advancementroll.mixin.features.noSlimeMoonSpawning;

import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    @Redirect(method = "canSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldAccess;getMoonSize()F"))
    private static float disableMoonSpawning(WorldAccess instance) {
        return 0.0F;
    }
}
