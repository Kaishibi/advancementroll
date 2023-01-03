package me.wurgo.advancementroll.mixin.features.insecureEndermen;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin extends HostileEntity {
    private PlayerEntity playerEntity;

    protected EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isPlayerStaring", at = @At("HEAD"))
    private void capturePlayer(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        this.playerEntity = player;
    }

    @Redirect(method = "isPlayerStaring", at = @At(value = "NEW", target = "net/minecraft/util/math/Vec3d"))
    private Vec3d wholeEntity(double x, double y, double z) {
        if (this.playerEntity != null) {
            return new Vec3d(x, this.getY() - this.playerEntity.getY(), z);
        }
        return new Vec3d(x, y, z);
    }
}
