package me.falu.advancementroll.mixin.features.largeSkeletonRange;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FollowTargetGoal.class)
public abstract class FollowTargetGoalMixin extends TrackTargetGoal {
    public FollowTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
        super(mob, checkVisibility);
    }

    @Override
    protected double getFollowRange() {
        if (this.mob.getType().equals(EntityType.SKELETON)) {
            return 80.0;
        }
        return super.getFollowRange();
    }
}
