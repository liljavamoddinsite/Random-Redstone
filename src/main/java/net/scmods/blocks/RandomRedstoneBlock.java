package net.scmods.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
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

public class RandomRedstoneBlock extends Block implements BlockEntityHost {
    public static final IntProperty DELAY = Properties.DELAY;
    public RandomRedstoneBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(DELAY, 1));
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
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RandomRedstoneBlockEntity(pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            BlockEntity bety = world.getBlockEntity(pos);
            if (bety instanceof RandomRedstoneBlockEntity) {
                world.setBlockState(pos, state.cycle(DELAY), 3);
                ((RandomRedstoneBlockEntity)bety).set(RandomRedstoneBlockEntity.interpretDelay(state.get(DELAY)));
            }
            player.sendMessage(Text.of("Changed delay to ".concat(String.valueOf((RandomRedstoneBlockEntity.interpretDelay(state.get(DELAY)) * 2))).concat(" ticks")), true);
            return ActionResult.success(world.isClient);
        }
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

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DELAY);
    }
}
