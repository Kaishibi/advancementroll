package me.falu.advancementroll.mixin.features.fishBigFleeRange;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishEntity.class)
public abstract class FishEntityMixin extends WaterCreatureEntity {
    protected FishEntityMixin(EntityType<? extends WaterCreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "initGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 1))
    private void biggerFleeRange(GoalSelector instance, int priority, Goal goal) {
        instance.add(priority, new FleeEntityGoal<>(this, PlayerEntity.class, 20.0f, 3.0, 3.0, EntityPredicates.EXCEPT_SPECTATOR::test));
    }
}
