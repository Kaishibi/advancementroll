package me.falu.advancementroll.mixin.features.noWeatherGradients;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Redirect(method = "renderWeather", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getRainGradient(F)F"))
    private float fixRain(ClientWorld instance, float v) {
        return MathHelper.lerp(v, ((WorldAccess) instance).getRainGradientPrev(), ((WorldAccess) instance).getRainGradient());
    }
}
