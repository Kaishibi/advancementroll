package me.falu.advancementroll.mixin.features.noAdvancedTooltips;

import net.minecraft.client.Keyboard;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Redirect(method = "processF3", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/GameOptions;write()V", ordinal = 0))
    private void disableAdvancedTooltips(GameOptions instance) {
        instance.advancedItemTooltips = false;
        instance.write();
    }
}
