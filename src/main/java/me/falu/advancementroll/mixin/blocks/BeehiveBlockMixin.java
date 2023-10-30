package me.falu.advancementroll.mixin.blocks;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @Inject(method = "randomDisplayTick", at = @At("HEAD"), cancellable = true)
    private void removeParticles(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        ci.cancel();
    }
}
