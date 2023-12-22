package dev.galiev.gofo.effect;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class WrathOfHeaven extends StatusEffect {

    public WrathOfHeaven(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            if (entity.isAlive()) {
                double x = entity.getX();
                double y = entity.getY();
                double z = entity.getZ();

                entity.setPosition(x, y, z);
                EntityType.LIGHTNING_BOLT.spawn((ServerWorld) entity.getWorld(), entity.getBlockPos(), SpawnReason.EVENT);
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
