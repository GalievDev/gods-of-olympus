package dev.galiev.gofo.event;

import dev.galiev.gofo.utils.GodsData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static dev.galiev.gofo.GodsOfOlympus.RANDOM;

public class ServerLifeCycle implements ServerTickEvents.StartWorldTick {
    private static final Random random = Random.create();
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
            if (counter == delay) {
                if (GodsData.isNeptuneHate(player) && !nPunished) {
                    //player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 350));
                    spawnGuardians(world, player);
                    nPunished = true;
                }
                if (GodsData.isJupiterHate(player) && !jPunished) {
                    createLighthing(player);
                    setTime(world);
                    jPunished = true;
                }
                counter = 0;
            } else counter++;
        });
    }

    private void spawnGuardians(World world, PlayerEntity player) {
        int rand = RANDOM.nextInt(1, 10);
        for (BlockPos pos : BlockPos.iterateRandomly(random, rand, player.getBlockPos(), 5)) {
            EntityType.GUARDIAN.spawn((ServerWorld) world, pos, SpawnReason.EVENT);
        }
    }

    private void createLighthing(PlayerEntity player) {
        int rand = RANDOM.nextInt(2, 5);
        EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), player.getBlockPos(), SpawnReason.EVENT);
        for (BlockPos pos : BlockPos.iterateRandomly(random, rand, player.getBlockPos(), 5)) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), pos, SpawnReason.EVENT);
        }
    }

    private void setTime(ServerWorld world) {
        if (world.isClient) return;
        world.setTimeOfDay(13000);
        world.setWeather(0, 10000, true, true);
    }
}
