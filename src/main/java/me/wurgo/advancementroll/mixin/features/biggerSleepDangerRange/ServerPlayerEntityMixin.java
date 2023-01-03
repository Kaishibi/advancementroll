package me.wurgo.advancementroll.mixin.features.biggerSleepDangerRange;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Predicate;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Redirect(method = "trySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;Ljava/util/function/Predicate;)Ljava/util/List;"))
    private List<? extends Entity> increaseDangerRange(World instance, Class<? extends Entity> entityClass, Box box, @Nullable Predicate<? super Entity> predicate) {
        box.expand(15);
        return instance.getEntities(entityClass, box, predicate);
    }
}
