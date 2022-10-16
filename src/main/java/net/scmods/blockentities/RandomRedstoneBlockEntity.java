package net.scmods.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.scmods.BlockEntityHost;
import net.scmods.RandomRedstone;

public class RandomRedstoneBlockEntity extends BlockEntity implements BlockEntityHost.Hosted {
    private int power = 0;
    private boolean latched = false;

    public RandomRedstoneBlockEntity(BlockPos pos, BlockState state) {
        super(RandomRedstone.RRB_BlockEntity, pos, state);
    }

    @Override
    public void tick()
    {
        this.latched = false;
        this.world.updateNeighbors(getPos(), getCachedState().getBlock());
    }

    public int getWeakRedstonePower()
    {
        if (!this.latched)
        {
            this.power = this.world.random.nextBetween(0, 15);
            this.latched = true;
        }
        return this.power;
    }
}
