package me.falu.advancementroll.mixin.features.shellDropChances;

import net.minecraft.entity.mob.DrownedEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DrownedEntity.class)
public class DrownedEntityMixin {
    @ModifyConstant(method = "initialize", constant = @Constant(floatValue = 2.0F))
    private float shellDropChances(float constant) {
        return 0.5F;
    }
}
