package dev.galiev.gofo.client.registry;

import dev.galiev.gofo.client.render.PoseidonTridentEntityRenderer;
import dev.galiev.gofo.registry.EntitiesRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntitiesRendererRegistry {
    public static void init() {
        EntityRendererRegistry.register(EntitiesRegistry.POSEIDON_TRIDENT_ENTITY, PoseidonTridentEntityRenderer::new);
    }
}
