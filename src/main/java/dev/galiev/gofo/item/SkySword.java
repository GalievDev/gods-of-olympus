package dev.galiev.gofo.item;

import dev.galiev.gofo.GodsOfOlympus;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class SkySword extends SwordItem {
    private static final int delay = 50;
    private static int counter = 0;
    public SkySword() {
        super(ToolMaterials.NETHERITE, 5, 0.6f, new Settings().fireproof());
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (counter < 0) counter = 0;
        if (target.isAlive() /*&& target instanceof Monster || target instanceof ZombieEntity*/) {
            target.takeKnockback(1.2, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
            if (target.isAlive()) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 150, 2));
                if (counter == delay) {
                    GodsOfOlympus.LOGGER.info("OASKIMFOSDMFL");
                    createLighthing(target);
                } else counter++;
            }
        }
        return super.postHit(stack, target, attacker);
    }

    private void createLighthing(LivingEntity entity) {
        if (!entity.isAlive()) {
            createLighthing(entity);
        } else {
            BlockPos.Mutable mutable = (BlockPos.Mutable) entity.getBlockPos();
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) entity.getWorld(), mutable, SpawnReason.EVENT);
            counter = 0;
        }
    }
}
