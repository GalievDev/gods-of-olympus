package dev.galiev.gofo.client;

import dev.galiev.gofo.client.registry.EntitiesRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public class GodsOfOlympusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntitiesRendererRegistry.init();
    }
}
