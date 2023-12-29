package dev.galiev.gofo;

import dev.galiev.gofo.event.PlayerKilledEntity;
import dev.galiev.gofo.event.ServerLifeCycle;
import dev.galiev.gofo.event.custom.PlayerKilledEntityCallback;
import dev.galiev.gofo.registry.BlocksRegistry;
import dev.galiev.gofo.registry.EffectsRegistry;
import dev.galiev.gofo.registry.EntitiesRegistry;
import dev.galiev.gofo.registry.ItemsRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class GodsOfOlympus implements ModInitializer {
    public static final String MOD_ID = "gofo";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Random RANDOM = new Random(System.currentTimeMillis());
    public static RegistryKey<ItemGroup> GODS_OF_OLYMPUS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID));

    @Override
    public void onInitialize() {
        ItemsRegistry.registerModItems();
        BlocksRegistry.registerModBlocks();
        EffectsRegistry.registerStatusEffect();
        EntitiesRegistry.registerModEntities();
        Registry.register(
                Registries.ITEM_GROUP, GODS_OF_OLYMPUS,
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(BlocksRegistry.ZEUS_STATUE))
                        .displayName(Text.of("Gods Of Olympus"))
                        .build()
        );
        PlayerKilledEntityCallback.EVENT.register(new PlayerKilledEntity());
        ServerTickEvents.START_WORLD_TICK.register(new ServerLifeCycle());
    }
}
