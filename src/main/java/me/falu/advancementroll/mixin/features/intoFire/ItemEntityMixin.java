package me.falu.advancementroll.mixin.features.intoFire;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow public abstract ItemStack getStack();

    private boolean placedFire = false;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void burnBlazeRods(CallbackInfo ci) {
        if (this.onGround && this.getStack().isItemEqualIgnoreDamage(new ItemStack(Items.BLAZE_ROD)) && !this.placedFire) {
            this.world.setBlockState(this.getBlockPos(), Blocks.FIRE.getDefaultState(), 0);
            this.placedFire = true;
        }
    }
}
