package dev.galiev.gofo.effect;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class WaterSuffocation extends StatusEffect {
    public WaterSuffocation(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            if (!entity.isInFluid()) {
                entity.damage(entity.getWorld().getDamageSources().drown(), 1);
            } else {
                BlockPos pos = entity.getBlockPos();
                for (BlockPos blockPos : BlockPos.iterateInSquare(pos, 1, Direction.UP, Direction.SOUTH)) {
                    if (entity.getWorld().getBlockState(blockPos).getFluidState().isIn(FluidTags.WATER)) {
                        entity.getWorld().setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                    }
                }
            }
        }

        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
