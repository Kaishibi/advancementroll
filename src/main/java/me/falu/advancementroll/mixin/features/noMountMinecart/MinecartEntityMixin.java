package me.falu.advancementroll.mixin.features.noMountMinecart;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecartEntity.class)
public abstract class MinecartEntityMixin extends AbstractMinecartEntity {
    protected MinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }
}
