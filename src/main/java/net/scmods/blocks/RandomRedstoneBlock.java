package net.scmods.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.scmods.RandomRedstone;
import net.scmods.blockentities.RandomRedstoneBlockEntity;
import org.jetbrains.annotations.Nullable;

public class RandomRedstoneBlock extends BlockWithEntity {

    public RandomRedstoneBlock(AbstractBlock.Settings settings) {
        super(settings);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, RandomRedstone.RRB_BlockEntity, (world1, pos, state1, be) -> RandomRedstoneBlockEntity.tick(world1, pos, state1, be));
    }
}
