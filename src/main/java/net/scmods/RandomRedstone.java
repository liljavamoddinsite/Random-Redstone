package net.scmods;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class RandomRedstone implements ModInitializer {
    public static final String MODID = "randomredstone";

//    public static final ItemGroup RR_GROUP = FabricItemGroupBuilder.create(new Identifier(MODID, "mfmitemgroup"))
//        .icon(() -> new ItemStack(typeLists.get(0).get(6)))
//        .build();

    @Override
    public void onInitialize() {

        // Add Furniture
//        for (int i = 0; i < furnitures.length; i++) {
//            ArrayList<Block> tempBlocks = typeLists.get(i);
//            for (int j = 0; j < vanillaLogs.length; j++) {
//                String currentName = vanillaLogs[j] + "_" + furnitures[i];
//                Registry.register(Registry.BLOCK, new Identifier(MODID, currentName), tempBlocks.get(j));
//                Registry.register(Registry.ITEM, new Identifier(MODID, currentName),
//                    new BlockItem(tempBlocks.get(j), new FabricItemSettings().group(RR_GROUP)));
//            }
//        }
    }
}
