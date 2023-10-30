package me.falu.advancementroll.mixin.features.singularPandaSpawns;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Biome.SpawnEntry.class)
public class SpawnEntryMixin {
    @Mutable @Shadow @Final public int maxGroupSize;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void singularPanda(EntityType<?> type, int weight, int minGroupSize, int maxGroupSize, CallbackInfo ci) {
        if (type.equals(EntityType.PANDA)) {
            this.maxGroupSize = 1;
        }
    }
}
