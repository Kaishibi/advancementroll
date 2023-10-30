package me.falu.advancementroll.mixin.features.noPortalRoomSpawner;

import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StrongholdGenerator.PortalRoom.class)
public abstract class PortalRoomMixin extends StrongholdGenerator.Piece {
    @Shadow private boolean spawnerPlaced;

    protected PortalRoomMixin(StructurePieceType structurePieceType, int i) {
        super(structurePieceType, i);
    }

    @Inject(method = "generate", at = @At("HEAD"))
    private void removeSpawner(ServerWorldAccess serverWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        this.spawnerPlaced = true;
    }
}
