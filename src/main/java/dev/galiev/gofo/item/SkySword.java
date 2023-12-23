package dev.galiev.gofo.item;

import dev.galiev.gofo.registry.EffectsRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;

public class SkySword extends SwordItem {
    public SkySword() {
        super(ToolMaterials.NETHERITE, 5, 0.6f, new Settings().fireproof());
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isAlive() && target instanceof Monster || target instanceof ZombieEntity || target instanceof RaiderEntity) {
            target.takeKnockback(1.0, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

            target.teleport(target.getX(), target.getY() + 5, target.getZ());
            target.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WRATH_OF_HEAVEN, 75, 1));
        }
        return super.postHit(stack, target, attacker);
    }
}
