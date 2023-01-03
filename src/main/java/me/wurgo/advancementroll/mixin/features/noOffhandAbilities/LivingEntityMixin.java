package me.wurgo.advancementroll.mixin.features.noOffhandAbilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Hand;values()[Lnet/minecraft/util/Hand;"))
    private Hand[] preventOffhandTotem() {
        return new Hand[] { Hand.MAIN_HAND };
    }
}
