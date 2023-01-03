package me.wurgo.advancementroll.mixin.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin {
    @Inject(method = "randomDisplayTick", at = @At("HEAD"), cancellable = true)
    private void removeAmbience(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "getLightLevel", at = @At("RETURN"), cancellable = true)
    private static void removeLightLevel(BlockState state, int maxLevel, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(maxLevel);
    }
}
