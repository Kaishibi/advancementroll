package me.wurgo.advancementroll.mixin.features.randomTntFuseTime;

import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(TntEntity.class)
public class TntEntityMixin {
    @Redirect(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/TntEntity;setFuse(I)V"))
    private void randomFuse(TntEntity instance, int fuse) {
        Random random = new Random();
        boolean b1 = random.nextBoolean();
        int offset = random.nextInt(30);
        instance.setFuse(b1 ? fuse + offset : fuse - offset);
    }
}
