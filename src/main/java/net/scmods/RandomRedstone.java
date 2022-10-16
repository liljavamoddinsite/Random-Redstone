package net.scmods;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.scmods.blocks.RandomRedstoneBlock;

import java.util.ArrayList;

public class RandomRedstone implements ModInitializer {
    public static final String MODID = "randomredstone";

    // Initialize Blocks
    public static final Settings RRB_Settings = FabricBlockSettings.of(Material.METAL)
            .strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque();
    public static final Block RRB_Block = new RandomRedstoneBlock(RRB_Settings);

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
        // Registr Blocks
        BlockRenderLayerMap.INSTANCE.putBlock(RRB_Block, RenderLayer.getCutout());
        register(RRB_Block, "random_redstone_block");
    }
}
