package me.falu.advancementroll.mixin.features.randomWitchHutCat;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CatEntity.class)
public class CatEntityMixin {
    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StructureStart;hasChildren()Z"))
    private boolean randomCat(StructureStart<?> instance) {
        return false;
    }
}
