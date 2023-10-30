package me.falu.advancementroll.mixin.features.noBoatMountShulker;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends GolemEntity {
    protected ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return entity.getType() != EntityType.BOAT;
    }
}
