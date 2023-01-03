package me.wurgo.advancementroll.mixin.features.scrambledEnchantmentNames;

import me.wurgo.advancementroll.Advancementroll;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    @Inject(method = "getTranslationKey", at = @At("RETURN"), cancellable = true)
    private void scrambleEnchantment(CallbackInfoReturnable<String> cir) {
        String word = I18n.translate(cir.getReturnValue());
        ArrayList<Character> chars = new ArrayList<>(word.length());
        for (char c : word.toCharArray()) { chars.add(c); }
        Collections.shuffle(chars, new Random(Advancementroll.screenSeed));
        char[] shuffled = new char[chars.size()];
        for (int i = 0; i < shuffled.length; i++) { shuffled[i] = chars.get(i); }
        cir.setReturnValue(new String(shuffled).toLowerCase(Locale.ROOT));
    }
}
