package me.falu.advancementroll.mixin.features.everythingIsBucket;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends Item {
    public BucketItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return "item.minecraft.bucket";
    }
}
