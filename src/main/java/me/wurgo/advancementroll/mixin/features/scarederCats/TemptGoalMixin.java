package me.wurgo.advancementroll.mixin.features.scarederCats;

import net.minecraft.entity.ai.goal.TemptGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TemptGoal.class)
public class TemptGoalMixin {
    @ModifyConstant(method = "shouldContinue", constant = @Constant(doubleValue = 5.0, ordinal = 1))
    private double increaseSensitivity(double constant) {
        return 0.1;
    }
}
