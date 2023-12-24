package dev.galiev.gofo.registry;

import dev.galiev.gofo.GodsOfOlympus;
import dev.galiev.gofo.entity.NeptuneTridentEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public class EntitiesRegistry {
    public static final EntityType<? extends NeptuneTridentEntity> NEPTUNE_TRIDENT_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "neptune_trident_entity"),
            FabricEntityTypeBuilder.<NeptuneTridentEntity>create(SpawnGroup.MISC, NeptuneTridentEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());

    public static void registerModEntities() {
        GodsOfOlympus.LOGGER.info("Registering Mod Entities for " + MOD_ID);
    }
}
