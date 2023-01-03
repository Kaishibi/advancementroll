package me.wurgo.advancementroll.mixin.features.hostileArbalistic;

import net.minecraft.advancement.criterion.KilledByCrossbowCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(KilledByCrossbowCriterion.class)
public class KilledByCrossbowCriterionMixin {
    @ModifyVariable(method = "trigger", at = @At("HEAD"), argsOnly = true)
    private Collection<Entity> removePassiveEntities(Collection<Entity> value) {
        Collection<Entity> newEntities = new ArrayList<>();
        for (Entity entity : value) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.isUndead() || livingEntity.getGroup() == EntityGroup.ILLAGER) {
                    newEntities.add(entity);
                }
            }
        }
        return newEntities;
    }
}
