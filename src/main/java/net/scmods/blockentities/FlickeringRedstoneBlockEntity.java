package net.scmods.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.scmods.BlockEntityHost;
import net.scmods.RandomRedstone;

public class FlickeringRedstoneBlockEntity extends BlockEntity implements BlockEntityHost.Hosted {
    private boolean enabled = true;
    private int power = 0;
    private int ticks = 0;
    private boolean latched = false;

    public FlickeringRedstoneBlockEntity(BlockPos pos, BlockState state) {
        super(RandomRedstone.FRB_BlockEntity, pos, state);
    }

    @Override
    public void tick()
    {
        if (ticks >= 1) {
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
            if (this.enabled)
                this.power = this.world.random.nextBetween(0, 1) * 15;
            else
                this.power = 0;
            this.latched = true;
        }
        return this.power;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putBoolean("enabled", enabled);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        enabled = nbt.getBoolean("enabled");
    }
}
