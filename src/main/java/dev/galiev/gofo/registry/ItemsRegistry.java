package dev.galiev.gofo.registry;

import dev.galiev.gofo.GodsOfOlympus;
import dev.galiev.gofo.item.NeptuneTrident;
import dev.galiev.gofo.item.SkySword;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public class ItemsRegistry {

    public static final Item SKY_SWORD = registerItem("sky_sword", new SkySword());
    public static final Item NEPTUNE_TRIDENT = registerItem("neptune_trident", new NeptuneTrident());

    private static Item registerItem(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(GodsOfOlympus.GODS_OF_OLYMPUS).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    public static void registerModItems() {
        GodsOfOlympus.LOGGER.info("Registering Mod Items for " + MOD_ID);
    }
}
