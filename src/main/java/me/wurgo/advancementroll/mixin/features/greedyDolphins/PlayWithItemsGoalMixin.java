package me.wurgo.advancementroll.mixin.features.greedyDolphins;

import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DolphinEntity.PlayWithItemsGoal.class)
public class PlayWithItemsGoalMixin {
    @Inject(method = "spitOutItem", at = @At("HEAD"), cancellable = true)
    private void keepItem(ItemStack stack, CallbackInfo ci) {
        ci.cancel();
    }
}
