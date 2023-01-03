package me.wurgo.advancementroll.mixin.features.explosionDropChance;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Shadow @Final private Random random;

    @Redirect(
            method = "affectWorld",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;dropStack(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V"
            )
    )
    private void explosionDropChance(World world, BlockPos pos, ItemStack stack) {
        if (this.random.nextFloat() >= 0.6F) {
            Block.dropStack(world, pos, stack);
        }
    }
}
