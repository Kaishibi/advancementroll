package me.falu.advancementroll.mixin.features.golemEveryWave;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Raid.class)
public class RaidMixin {
    @Shadow @Final private ServerWorld world;
    @Shadow @Final private Random random;

    @Inject(method = "spawnNextWave", at = @At("HEAD"))
    private void spawnGolem(BlockPos pos, CallbackInfo ci) {
        int bound = 20;
        boolean b1 = this.random.nextBoolean();
        boolean b2 = this.random.nextBoolean();
        BlockPos pos1 = pos.add((b1 ? 1 : -1) * this.random.nextInt(bound), 0, (b2 ? 1 : -1) * this.random.nextInt(bound));
        BlockPos pos2 = this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos1);
        IronGolemEntity entity = EntityType.IRON_GOLEM.create(this.world, null, null, null, pos2, SpawnReason.MOB_SUMMONED, false, false);
        this.world.spawnEntity(entity);
    }
}
