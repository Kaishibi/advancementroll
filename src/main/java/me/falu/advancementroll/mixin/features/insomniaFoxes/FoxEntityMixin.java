package me.falu.advancementroll.mixin.features.insomniaFoxes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(FoxEntity.class)
public class FoxEntityMixin {
    @Mutable @Shadow @Final private static Predicate<Entity> NOTICEABLE_PLAYER_FILTER;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void editPlayerFilter(CallbackInfo ci) {
        NOTICEABLE_PLAYER_FILTER = EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR;
    }

    @Redirect(method = "initGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 1))
    private void removeStopWandering(GoalSelector instance, int priority, Goal goal) {}

    @Redirect(method = "initGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 11))
    private void removeSleeping(GoalSelector instance, int priority, Goal goal) {}
}
