package me.wurgo.advancementroll.mixin.features.noAdvancedTooltips;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final public GameOptions options;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void disableAdvancedTooltips(RunArgs args, CallbackInfo ci) {
        this.options.advancedItemTooltips = false;
        this.options.write();
    }
}
