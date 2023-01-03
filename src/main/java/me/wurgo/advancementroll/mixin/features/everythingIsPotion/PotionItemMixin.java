package me.wurgo.advancementroll.mixin.features.everythingIsPotion;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PotionItem.class)
public class PotionItemMixin {
    @Inject(method = "getTranslationKey", at = @At("RETURN"), cancellable = true)
    private void removeName(ItemStack stack, CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("item.minecraft.potion");
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void removeLore(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        ci.cancel();
    }
}
