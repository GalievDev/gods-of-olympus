package dev.galiev.gofo.registry;

import dev.galiev.gofo.GodsOfOlympus;
import dev.galiev.gofo.item.PoseidonTrident;
import dev.galiev.gofo.item.ZeusLightning;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public class ItemsRegistry {

    public static final Item ZEUS_LIGHTNING = registerItem("zeus_lightning", new ZeusLightning());
    public static final Item POSEIDON_TRIDENT = registerItem("poseidon_trident", new PoseidonTrident());

    private static Item registerItem(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(GodsOfOlympus.GODS_OF_OLYMPUS).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    public static void registerModItems() {
        GodsOfOlympus.LOGGER.info("Registering Mod Items for " + MOD_ID);
    }
}
