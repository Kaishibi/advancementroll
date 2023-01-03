package me.wurgo.advancementroll.mixin.items;

import net.minecraft.item.EnchantedGoldenAppleItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantedGoldenAppleItem.class)
public class EnchantedGoldenAppleItemMixin {
    @Inject(method = "hasGlint", at = @At("RETURN"), cancellable = true)
    private void removeGlint(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
