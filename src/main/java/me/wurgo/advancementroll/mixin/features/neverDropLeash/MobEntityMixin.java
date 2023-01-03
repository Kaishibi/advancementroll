package me.wurgo.advancementroll.mixin.features.neverDropLeash;

import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @ModifyVariable(method = "detachLeash", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private boolean neverDropLeash(boolean value) {
        return false;
    }
}
