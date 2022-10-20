package net.scmods;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.scmods.blockentities.FlickeringRedstoneBlockEntity;
import net.scmods.blockentities.RandomRedstoneBlockEntity;
import net.scmods.blockentities.WaveformRedstoneBlockEntity;
import net.scmods.blocks.FlickeringRedstoneBlock;
import net.scmods.blocks.RandomRedstoneBlock;
import net.scmods.blocks.WaveformRedstoneBlock;

public class RandomRedstone implements ModInitializer {
    public static final String MODID = "randomredstone";

    // Initialize Blocks
    public static final Settings RRB_Settings = FabricBlockSettings.of(Material.METAL)
            .strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque();
    public static final Block RRB_Block = new RandomRedstoneBlock(RRB_Settings);
    public static final BlockEntityType<RandomRedstoneBlockEntity> RRB_BlockEntity = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("randomredstone", "random_redstone_block_entity"),
            FabricBlockEntityTypeBuilder.create(RandomRedstoneBlockEntity::new, RRB_Block).build()
    );

    public static final Settings WRB_Settings = FabricBlockSettings.of(Material.METAL)
            .strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque();
    public static final Block WRB_Block = new WaveformRedstoneBlock(WRB_Settings);
    public static final BlockEntityType<WaveformRedstoneBlockEntity> WRB_BlockEntity = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("randomredstone", "waveform_redstone_block_entity"),
            FabricBlockEntityTypeBuilder.create(WaveformRedstoneBlockEntity::new, WRB_Block).build()
    );

    public static final Settings FRB_Settings = FabricBlockSettings.of(Material.METAL)
            .strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque();
    public static final Block FRB_Block = new FlickeringRedstoneBlock(FRB_Settings);
    public static final BlockEntityType<FlickeringRedstoneBlockEntity> FRB_BlockEntity = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("randomredstone", "flickering_redstone_block_entity"),
            FabricBlockEntityTypeBuilder.create(FlickeringRedstoneBlockEntity::new, FRB_Block).build()
    );

    // Create Group
    public static final ItemGroup RR_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "randomredstoneitemgroup"))
            .icon(() -> new ItemStack(RRB_Block))
            .build();

    public void register(Block block, String name) {
        Registry.register(Registry.BLOCK, new Identifier(MODID, name), block);
        Registry.register(Registry.ITEM, new Identifier(MODID, name),
                new BlockItem(block, new FabricItemSettings().group(RR_GROUP)));
    }

    @Override
    public void onInitialize() {
        // Register Blocks
        BlockRenderLayerMap.INSTANCE.putBlock(RRB_Block, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WRB_Block, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FRB_Block, RenderLayer.getCutout());
        register(RRB_Block, "random_redstone_block");
        register(WRB_Block, "waveform_redstone_block");
        register(FRB_Block, "flickering_redstone_block");
    }
}
