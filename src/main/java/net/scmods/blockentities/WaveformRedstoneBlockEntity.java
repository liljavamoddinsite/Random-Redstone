package net.scmods.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.scmods.BlockEntityHost;
import net.scmods.RandomRedstone;

public class WaveformRedstoneBlockEntity extends BlockEntity implements BlockEntityHost.Hosted {
    private int power = 0;
    private int delay = 1;
    private float period = (float)(Math.PI * 2.0 / 10);
    private int ticks = 0;
    private boolean latched = false;

    public WaveformRedstoneBlockEntity(BlockPos pos, BlockState state) {
        super(RandomRedstone.RRB_BlockEntity, pos, state);
    }

    public void set(int value) {
        delay = value;
        period = (float)(Math.PI * 2.0 / (value * 10));
    }

    @Override
    public void tick()
    {
        if (ticks >= (delay * 10)) {
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
            this.power = (int)((Math.cos(period * Math.floor(ticks)) + 1) / 2 * 15);
            this.latched = true;
        }
        return this.power;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("delay", delay);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        delay = nbt.getInt("delay");
    }
}
