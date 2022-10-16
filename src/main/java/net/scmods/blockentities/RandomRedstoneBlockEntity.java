package net.scmods.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.scmods.BlockEntityHost;
import net.scmods.RandomRedstone;

public class RandomRedstoneBlockEntity extends BlockEntity implements BlockEntityHost.Hosted {
    private int power = 0;
    private int delay = 0;
    private int ticks = 0;
    private boolean latched = false;

    public RandomRedstoneBlockEntity(BlockPos pos, BlockState state) {
        super(RandomRedstone.RRB_BlockEntity, pos, state);
    }

    public void set(int value) {
        delay = value;
    }

    @Override
    public void tick()
    {
        if (ticks >= delay * 2) {
            this.latched = false;
            assert this.world != null;
            this.world.updateNeighbors(getPos(), getCachedState().getBlock());
            ticks = 0;
        } else ticks++;
    }

    public int getWeakRedstonePower()
    {
        if (!this.latched)
        {
            assert this.world != null;
            this.power = this.world.random.nextBetween(0, 15);
            this.latched = true;
        }
        return this.power;
    }
}
