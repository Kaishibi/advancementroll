package me.wurgo.advancementroll.mixin.features.noWeatherGradients;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(World.class)
public interface WorldAccess {
    @Accessor("rainGradientPrev") float getRainGradientPrev();
    @Accessor("rainGradient") float getRainGradient();
}
