package dev.galiev.gofo.registry;

import dev.galiev.gofo.GodsOfOlympus;
import dev.galiev.gofo.effect.WaterSuffocation;
import dev.galiev.gofo.effect.WrathOfHeaven;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectsRegistry {
    public static StatusEffect WATER_SUFFOCATION = new WaterSuffocation(StatusEffectCategory.HARMFUL, 3124687);
    public static StatusEffect WRATH_OF_HEAVEN = new WrathOfHeaven(StatusEffectCategory.HARMFUL, 3433423);

    public static void registerStatusEffect() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(GodsOfOlympus.MOD_ID, "water_suffocation"), WATER_SUFFOCATION);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(GodsOfOlympus.MOD_ID, "wrath_of_heaven"), WRATH_OF_HEAVEN);
    }
}
