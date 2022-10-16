package net.scmods.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.scmods.BlockEntityHost;
import net.scmods.RandomRedstone;
import net.scmods.blockentities.RandomRedstoneBlockEntity;

public class RandomRedstoneBlock extends Block implements BlockEntityHost {
    public RandomRedstoneBlock(AbstractBlock.Settings settings) {
        super(settings);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntityType<RandomRedstoneBlockEntity> getBlockEntityType() {
        return RandomRedstone.RRB_BlockEntity;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RandomRedstoneBlockEntity(blockPos, blockState);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (world.getBlockEntity(pos) instanceof RandomRedstoneBlockEntity rng)
            return rng.getWeakRedstonePower();
        return 0;
    }
}
