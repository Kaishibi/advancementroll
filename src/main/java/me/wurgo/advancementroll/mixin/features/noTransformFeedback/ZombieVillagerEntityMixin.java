package me.wurgo.advancementroll.mixin.features.noTransformFeedback;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieVillagerEntity.class)
public class ZombieVillagerEntityMixin {
    @Redirect(method = "handleStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V"))
    private void removeCureFeedback(World instance, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean bl) {}
}
