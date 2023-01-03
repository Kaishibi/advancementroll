package me.wurgo.advancementroll.mixin.client;

import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ReloadableResourceManagerImpl.class)
public class ReloadableResourceManagerImplMixin {
    @ModifyVariable(method = "beginMonitoredReload", at = @At("HEAD"), argsOnly = true)
    private List<ResourcePack> removePacks(List<ResourcePack> value) {
        List<ResourcePack> packs = new ArrayList<>();
        for (ResourcePack pack : value) {
            if (pack.getName().equalsIgnoreCase("Default")) {
                packs.add(pack);
                break;
            }
        }
        return packs;
    }
}
