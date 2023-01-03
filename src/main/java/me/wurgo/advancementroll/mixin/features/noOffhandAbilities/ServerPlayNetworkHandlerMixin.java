package me.wurgo.advancementroll.mixin.features.noOffhandAbilities;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Redirect(method = "onPlayerInteractItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;interactItem(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"))
    private ActionResult preventOffhandShield(ServerPlayerInteractionManager instance, ServerPlayerEntity player, World world, ItemStack stack, Hand hand) {
        if (hand == Hand.OFF_HAND && stack.getItem() == Items.SHIELD) {
            return null;
        }
        return instance.interactItem(player, world, stack, hand);
    }

    @Redirect(method = "onPlayerInteractItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;shouldSwingHand()Z"))
    private boolean nullCheck(ActionResult instance) {
        if (instance == null) {
            return false;
        }
        return instance.shouldSwingHand();
    }
}
