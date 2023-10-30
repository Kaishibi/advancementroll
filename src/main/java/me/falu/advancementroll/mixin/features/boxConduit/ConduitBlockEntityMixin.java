package me.falu.advancementroll.mixin.features.boxConduit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ConduitBlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ConduitBlockEntity.class)
public abstract class ConduitBlockEntityMixin extends BlockEntity {
    @Shadow @Final private List<BlockPos> activatingBlocks;
    @Shadow @Final private static Block[] ACTIVATING_BLOCKS;
    @Shadow protected abstract void setEyeOpen(boolean eyeOpen);

    public ConduitBlockEntityMixin(BlockEntityType<?> type) {
        super(type);
    }

    /**
     * @author Maya
     * @reason box lmao
     */
    @Overwrite
    private boolean updateActivatingBlocks() {
        this.activatingBlocks.clear();

        if (this.world == null) { return false; }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    BlockPos pos = this.pos.add(i, j, k);
                    BlockState state = this.world.getBlockState(pos);
                    for (Block block : ACTIVATING_BLOCKS) {
                        if (!state.isOf(block)) continue;
                        this.activatingBlocks.add(pos);
                    }
                }
            }
        }

        this.setEyeOpen(this.activatingBlocks.size() >= 328);
        return this.activatingBlocks.size() >= 124;
    }
}
