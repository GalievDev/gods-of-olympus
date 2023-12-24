package dev.galiev.gofo.client.registry;

import dev.galiev.gofo.client.render.NeptuneTridentEntityRenderer;
import dev.galiev.gofo.registry.EntitiesRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntitiesRendererRegistry {
    public static void init() {
        EntityRendererRegistry.register(EntitiesRegistry.NEPTUNE_TRIDENT_ENTITY, NeptuneTridentEntityRenderer::new);
    }
}
