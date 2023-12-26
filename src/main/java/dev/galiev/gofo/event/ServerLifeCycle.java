package dev.galiev.gofo.event;

import dev.galiev.gofo.registry.EffectsRegistry;
import dev.galiev.gofo.utils.GodsData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static dev.galiev.gofo.GodsOfOlympus.RANDOM;

public class ServerLifeCycle implements ServerTickEvents.StartWorldTick {
    private static final int delay = RANDOM.nextInt(7000, 15000);
    private static int counter = 0;
    private static boolean nPunished = false;
    private static boolean jPunished = false;

    @Override
    public void onStartTick(ServerWorld world) {
        int nDelay = delay - 1000;
        int jDelay = delay - 2000;
        if (counter == nDelay) nPunished = false;
        if (counter == jDelay) jPunished = false;
        if (counter < 0) counter = 0;

        world.getPlayers().forEach(player -> {
            if (GodsData.isZeusLike(player)) {
                if (world.isRaining()) {
                    for (BlockPos pos : BlockPos.iterate(player.getBlockX() - 100, player.getBlockY(), player.getBlockZ() - 100, player.getBlockX() + 100, player.getBlockY(), player.getBlockZ() + 100)) {
                        var state = world.getBlockState(pos);
                        if (state.getBlock() instanceof CropBlock block && block.canGrow(world, world.getRandom(), pos, state)) {
                            block.grow(world, world.getRandom(), pos, state);
                        }
                    }
                }
            }
            if (counter == 1000) {
                int chance = RANDOM.nextInt(1, 100);
                //Poseidon Events
                if (!nPunished) {
                    if (GodsData.isPoseidonHate(player)) {
                        if (chance <= 49) {
                            player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 250));
                        } else {
                            spawnGuardians(world, player);
                        }
                    }
                    if (GodsData.isPoseidonLike(player)) {
                        if (chance <= 49) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 10000));
                        } else {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 10000));
                        }
                    }
                    nPunished = true;
                }
                //Zeus Events
                if (!jPunished) {
                    if (GodsData.isZeusHate(player)) {
                        if (chance <= 32) {
                            createLightning(player);
                        } else if (chance <= 64) {
                            setTime(world);
                        } else {
                            player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WRATH_OF_HEAVEN, 50));
                        }
                    }
                    if (GodsData.isZeusLike(player)) {
                        if (chance <= 49) {
                            world.setWeather(0, 1200, true, false);
                        } else {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 10000));
                        }
                    }
                    jPunished = true;
                }
                counter = 0;
            } else counter++;
        });
    }

    private void spawnGuardians(World world, PlayerEntity player) {
        int rand = RANDOM.nextInt(3, 10);
        for (BlockPos pos : BlockPos.iterateRandomly(world.getRandom(), rand, player.getBlockPos(), 5)) {
            EntityType.GUARDIAN.spawn((ServerWorld) world, pos, SpawnReason.EVENT);
        }
    }

    private void createLightning(PlayerEntity player) {
        int rand = RANDOM.nextInt(3, 10);
        EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), player.getBlockPos(), SpawnReason.EVENT);
        for (BlockPos pos : BlockPos.iterateRandomly(player.getRandom(), rand, player.getBlockPos(), 5)) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), pos, SpawnReason.EVENT);
        }
    }

    private void setTime(ServerWorld world) {
        if (world.isClient) return;
        world.setTimeOfDay(13000);
        world.setWeather(0, 10000, true, true);
    }
}
