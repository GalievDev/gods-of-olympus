package dev.galiev.gofo.registry;

import dev.galiev.gofo.GodsOfOlympus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public class BlocksRegistry {
    
    public static final Block JUPITER_STATUE = registerBlock("jupiter_statue",
            new Block(FabricBlockSettings.create().strength(-1)), GodsOfOlympus.GODS_OF_OLYMPUS);

    private static Block registerBlock(String name, Block block, RegistryKey<ItemGroup> group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(GodsOfOlympus.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, RegistryKey<ItemGroup> group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(GodsOfOlympus.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        GodsOfOlympus.LOGGER.info("Registering Mod Blocks for " + MOD_ID);
    }
}
