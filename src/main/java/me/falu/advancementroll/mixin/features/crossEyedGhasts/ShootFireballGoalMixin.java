package me.falu.advancementroll.mixin.features.crossEyedGhasts;

import net.minecraft.entity.mob.GhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(GhastEntity.ShootFireballGoal.class)
public class ShootFireballGoalMixin {
    private final int offset = 5;

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/GhastEntity;getX()D", ordinal = 0))
    private double makeInaccurate1(GhastEntity instance) {
        return new Random().nextBoolean() ? instance.getX() * this.offset : instance.getX() / this.offset;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/GhastEntity;getZ()D", ordinal = 0))
    private double makeInaccurate2(GhastEntity instance) {
        return new Random().nextBoolean() ? instance.getZ() * this.offset : instance.getZ() / this.offset;
    }
}
