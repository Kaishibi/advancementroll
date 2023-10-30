package me.falu.advancementroll.mixin.features.everythingIsBucket;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin extends Item {
    public MilkBucketItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return "item.minecraft.bucket";
    }
}
