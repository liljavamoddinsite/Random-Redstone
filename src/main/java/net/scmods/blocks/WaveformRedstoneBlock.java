package net.scmods.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.scmods.BlockEntityHost;
import net.scmods.RandomRedstone;
import net.scmods.blockentities.RandomRedstoneBlockEntity;
import net.scmods.blockentities.WaveformRedstoneBlockEntity;

public class WaveformRedstoneBlock extends Block implements BlockEntityHost {
    public static final IntProperty DELAY = Properties.DELAY;
    public WaveformRedstoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(DELAY, 1));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntityType<WaveformRedstoneBlockEntity> getBlockEntityType() {
        return RandomRedstone.WRB_BlockEntity;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WaveformRedstoneBlockEntity(pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            BlockEntity bety = world.getBlockEntity(pos);
            if (bety instanceof WaveformRedstoneBlockEntity) {
                world.setBlockState(pos, state.cycle(DELAY), 3);
                ((WaveformRedstoneBlockEntity)bety).set(WaveformRedstoneBlockEntity.interpretDelay(state.get(DELAY)));
            }
            player.sendMessage(Text.of("Changed Period to ".concat(
                    String.valueOf((WaveformRedstoneBlockEntity.interpretDelay(state.get(DELAY)) * 20 + 10))
            ).concat(" ticks")), true);
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (world.getBlockEntity(pos) instanceof WaveformRedstoneBlockEntity rng)
            return rng.getWeakRedstonePower();
        return 0;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DELAY);
    }
}
