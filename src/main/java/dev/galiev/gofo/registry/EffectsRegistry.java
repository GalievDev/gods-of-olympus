package dev.galiev.gofo.registry;

import dev.galiev.gofo.GodsOfOlympus;
import dev.galiev.gofo.effect.WaterSuffocation;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectsRegistry {
    public static StatusEffect WATER_SUFFOCATION;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(GodsOfOlympus.MOD_ID, name),
            new WaterSuffocation(StatusEffectCategory.HARMFUL, 3124687));
    }

    public static void registerEffects() {
        WATER_SUFFOCATION = registerStatusEffect("water_suffocation");
    }
}
