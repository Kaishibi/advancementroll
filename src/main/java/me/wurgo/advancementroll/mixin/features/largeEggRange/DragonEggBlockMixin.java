package me.wurgo.advancementroll.mixin.features.largeEggRange;

import net.minecraft.block.DragonEggBlock;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DragonEggBlock.class)
public class DragonEggBlockMixin {
    @Redirect(method = "teleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;add(III)Lnet/minecraft/util/math/BlockPos;"))
    private BlockPos biggerRange(BlockPos instance, int x, int y, int z) {
        int multiplier = 10;
        return instance.add(x * multiplier, y * multiplier, z * multiplier);
    }
}
