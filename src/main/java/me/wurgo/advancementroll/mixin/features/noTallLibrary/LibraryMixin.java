package me.wurgo.advancementroll.mixin.features.noTallLibrary;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StrongholdGenerator.Library.class)
public class LibraryMixin {
    @Mutable @Shadow @Final private boolean tall;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void neverTall(CallbackInfo ci) {
        this.tall = false;
    }
}
