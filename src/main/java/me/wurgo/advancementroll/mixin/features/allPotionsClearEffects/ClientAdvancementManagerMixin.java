package me.wurgo.advancementroll.mixin.features.allPotionsClearEffects;

import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientAdvancementManager;
import net.minecraft.network.packet.s2c.play.AdvancementUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ClientAdvancementManager.class)
public class ClientAdvancementManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onAdvancements", at = @At("RETURN"))
    private void onAdvancementComplete(AdvancementUpdateS2CPacket packet, CallbackInfo ci) {
        Map<Identifier, AdvancementProgress> advancementsToEarn = packet.getAdvancementsToProgress();

        for (Map.Entry<Identifier, AdvancementProgress> advancementEntry : advancementsToEarn.entrySet()) {
            if (advancementEntry.getValue().isDone() && advancementEntry.getKey().toString().equals("minecraft:nether/all_potions")) {
                if (this.client.getServer() != null && this.client.player != null) {
                    ServerPlayerEntity serverPlayer = this.client.getServer().getPlayerManager().getPlayer(this.client.player.getUuid());
                    if (serverPlayer != null) {
                        serverPlayer.clearStatusEffects();
                    }
                }
            }
        }
    }
}
