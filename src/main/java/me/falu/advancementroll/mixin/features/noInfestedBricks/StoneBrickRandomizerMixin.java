package me.falu.advancementroll.mixin.features.noInfestedBricks;

import net.minecraft.block.Blocks;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(StrongholdGenerator.StoneBrickRandomizer.class)
public abstract class StoneBrickRandomizerMixin extends StructurePiece.BlockRandomizer {
    @Inject(method = "setBlock", at = @At("RETURN"))
    private void pestControl(Random random, int x, int y, int z, boolean placeBlock, CallbackInfo ci) {
        float f;
        this.block = placeBlock ? ((f = random.nextFloat()) < 0.2f ? Blocks.CRACKED_STONE_BRICKS.getDefaultState() : (f < 0.5f ? Blocks.MOSSY_STONE_BRICKS.getDefaultState() : (Blocks.STONE_BRICKS.getDefaultState()))) : Blocks.CAVE_AIR.getDefaultState();
    }
}
