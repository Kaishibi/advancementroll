package me.wurgo.advancementroll.mixin.features.aggressiveBees;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity {
    @Shadow public abstract void setAngryAt(@Nullable UUID uuid);
    @Shadow public abstract void setAngerTime(int ticks);

    private int ticksSinceCheck = 0;
    private boolean isAngry = false;

    protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickAnger(CallbackInfo ci) {
        int checkOffset = 10;
        int playerCheckDelay = 20;
        int angerTime = 300;

        this.ticksSinceCheck++;

        if (!this.isAngry && this.ticksSinceCheck >= playerCheckDelay && this.world != null) {
            List<Entity> entities = this.world.getEntities(null, new Box(new BlockPos(this.getPos())).expand(checkOffset, checkOffset, checkOffset));
            List<PlayerEntity> players = new ArrayList<>();
            for (Entity entity : entities) {
                if (entity.getType().equals(EntityType.PLAYER)) {
                    players.add((PlayerEntity) entity);
                }
            }

            if (players.size() > 0) {
                PlayerEntity player;
                if (players.size() > 1) { player = players.get(new Random().nextInt(players.size() - 1)); }
                else { player = players.get(0); }
                this.setAngryAt(player.getUuid());
                this.setAngerTime(angerTime);
                this.isAngry = true;
            }

            this.ticksSinceCheck = 0;
        } else if (this.ticksSinceCheck >= angerTime && this.isAngry) {
            this.isAngry = false;
        }
    }
}
