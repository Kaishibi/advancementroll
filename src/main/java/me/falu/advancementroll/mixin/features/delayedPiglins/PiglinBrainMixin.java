package me.falu.advancementroll.mixin.features.delayedPiglins;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    private static int tickCount = 0;

    @Redirect(method = "tickActivities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/Brain;resetPossibleActivities(Ljava/util/List;)V"))
    private static void addAdmireTickDelay(Brain<PiglinEntity> instance, List<Activity> list) {
        int tickDelay = 100;
        ++tickCount;

        if (tickCount == tickDelay) {
            tickCount = 0;
            instance.resetPossibleActivities(list);
            return;
        }
        if (!instance.hasActivity(Activity.ADMIRE_ITEM)) {
            instance.resetPossibleActivities(ImmutableList.of(Activity.FIGHT, Activity.AVOID, Activity.CELEBRATE, Activity.RIDE, Activity.IDLE));
        }
    }

    @Inject(method = "getNearestDetectedPlayer", at = @At("HEAD"), cancellable = true)
    private static void removeLineOfSight(PiglinEntity piglin, CallbackInfoReturnable<Optional<PlayerEntity>> cir) {
        if (piglin.getBrain().hasMemoryModule(MemoryModuleType.NEAREST_PLAYERS)) {
            cir.setReturnValue(Optional.of(piglin.getBrain().getOptionalMemory(MemoryModuleType.NEAREST_PLAYERS).get().get(0)));
        }
        cir.setReturnValue(Optional.empty());
    }
}
