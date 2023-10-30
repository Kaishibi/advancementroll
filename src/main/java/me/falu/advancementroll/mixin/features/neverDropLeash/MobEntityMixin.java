package me.falu.advancementroll.mixin.features.neverDropLeash;

import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @ModifyVariable(method = "detachLeash", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private boolean neverDropLeash(boolean value) {
        return false;
    }
}
