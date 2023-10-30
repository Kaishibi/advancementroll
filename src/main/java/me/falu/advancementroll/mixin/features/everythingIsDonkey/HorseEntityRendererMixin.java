package me.falu.advancementroll.mixin.features.everythingIsDonkey;

import com.google.common.collect.Maps;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(HorseEntityRenderer.class)
public class HorseEntityRendererMixin {
    @Mutable @Shadow @Final private static Map<HorseColor, Identifier> TEXTURES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void donkeyTexture(CallbackInfo ci) {
        TEXTURES = Util.make(Maps.newEnumMap(HorseColor.class), enumMap -> {
            enumMap.put(HorseColor.WHITE, new Identifier("textures/entity/horse/donkey.png"));
            enumMap.put(HorseColor.CREAMY, new Identifier("textures/entity/horse/donkey.png"));
            enumMap.put(HorseColor.CHESTNUT, new Identifier("textures/entity/horse/donkey.png"));
            enumMap.put(HorseColor.BROWN, new Identifier("textures/entity/horse/donkey.png"));
            enumMap.put(HorseColor.BLACK, new Identifier("textures/entity/horse/donkey.png"));
            enumMap.put(HorseColor.GRAY, new Identifier("textures/entity/horse/donkey.png"));
            enumMap.put(HorseColor.DARKBROWN, new Identifier("textures/entity/horse/donkey.png"));
        });
    }
}
