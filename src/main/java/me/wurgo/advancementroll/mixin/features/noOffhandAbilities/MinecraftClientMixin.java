package me.wurgo.advancementroll.mixin.features.noOffhandAbilities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Redirect(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;interactItem(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"))
    private ActionResult preventOffhandTotemAndShield(ClientPlayerInteractionManager instance, PlayerEntity player, World world, Hand hand) {
        if (hand == Hand.OFF_HAND) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.getItem() == Items.SHIELD) {
                return null;
            }
        }
        return instance.interactItem(player, world, hand);
    }

    @Redirect(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;isAccepted()Z"))
    private boolean nullCheck(ActionResult instance) {
        if (instance == null) {
            return false;
        }
        return instance.isAccepted();
    }
}
