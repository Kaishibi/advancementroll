package me.falu.advancementroll.mixin.features.alwaysInvisibleSpiders;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpiderEntity.class)
public abstract class SpiderEntityMixin extends HostileEntity {
    protected SpiderEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private void alwaysInvisible(WorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CompoundTag entityTag, CallbackInfoReturnable<EntityData> cir) {
        cir.cancel();

        StatusEffect statusEffect;
        entityData = super.initialize(world, difficulty, spawnReason, entityData, entityTag);
        if (world.getRandom().nextInt(100) == 0) {
            SkeletonEntity skeletonEntity = EntityType.SKELETON.create(this.world);
            if (skeletonEntity != null) {
                skeletonEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.yaw, 0.0f);
                skeletonEntity.initialize(world, difficulty, spawnReason, null, null);
                skeletonEntity.startRiding(this);
                world.spawnEntity(skeletonEntity);
            }
        }
        if (entityData == null) {
            entityData = new SpiderEntity.SpiderData();
            ((SpiderEntity.SpiderData) entityData).effect = StatusEffects.INVISIBILITY;
        }
        if (entityData instanceof SpiderEntity.SpiderData && (statusEffect = ((SpiderEntity.SpiderData)entityData).effect) != null) {
            this.addStatusEffect(new StatusEffectInstance(statusEffect, Integer.MAX_VALUE));
        }
        cir.setReturnValue(entityData);
    }
}
