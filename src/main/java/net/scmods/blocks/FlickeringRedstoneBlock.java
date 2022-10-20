package net.scmods.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
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
import net.scmods.blockentities.FlickeringRedstoneBlockEntity;
import net.scmods.blockentities.RandomRedstoneBlockEntity;

public class FlickeringRedstoneBlock extends Block implements BlockEntityHost {
    public static final BooleanProperty ENABLED = Properties.ENABLED;

    public FlickeringRedstoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ENABLED, true));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntityType<FlickeringRedstoneBlockEntity> getBlockEntityType() {
        return RandomRedstone.FRB_BlockEntity;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FlickeringRedstoneBlockEntity(pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        } else {
            BlockEntity bety = world.getBlockEntity(pos);
            if (bety instanceof FlickeringRedstoneBlockEntity)
                world.setBlockState(pos, state.cycle(ENABLED), 3);
            if (state.get(ENABLED))
                player.sendMessage(Text.of("Flickerer Enabled"), true);
            else
                player.sendMessage(Text.of("Flickerer Disabled"), true);
            return ActionResult.success(world.isClient);
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (world.getBlockEntity(pos) instanceof FlickeringRedstoneBlockEntity rng)
            return rng.getWeakRedstonePower();
        return 0;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ENABLED);
    }
}
