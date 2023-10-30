package me.falu.advancementroll.mixin.features.scrambledEnchantmentNames;

import me.falu.advancementroll.Advancementroll;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "openScreen", at = @At("TAIL"))
    private void incrementScreenSeed(Screen screen, CallbackInfo ci) {
        Advancementroll.screenSeed++;
    }
}
