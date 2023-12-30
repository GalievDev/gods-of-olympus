package dev.galiev.gofo.client.registry;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModelsPredicatiesProvidersRegistry {
    public static void init() {
        ModelPredicateProviderRegistry.register(
                Registries.ITEM.get(new Identifier("minecraft", "poseidon_trident")),
                new Identifier("throwing"),
                (itemStack, clientWorld, livingEntity, i) -> {
                    if (livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack) {
                        return 1.0f;
                    } else {
                        return 0.0f;
                    }
                }
        );
    }
}

