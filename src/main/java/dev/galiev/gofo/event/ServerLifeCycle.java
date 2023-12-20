package dev.galiev.gofo.event;

import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
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
    private static final int delay = 400;
    private static int counter = 0;
    private static boolean nPunished = false;
    private static boolean jPunished = false;

    @Override
    public void onStartTick(ServerWorld world) {
        int nDelay = 300;
        int jDelay = 200;
        if (counter == nDelay) nPunished = false;
        if (counter == jDelay) jPunished = false;
        if (counter < 0) counter = 0;

        if (counter == delay) {
            world.getPlayers().forEach (player -> {
               if (GodsData.isNeptuneHate((IPlayerDataSaver) player) && !nPunished) {
                   //player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 350));
                   spawnGuardians(world, player);
                   nPunished = true;
               }
               if (GodsData.isJupiterHate((IPlayerDataSaver) player) && !jPunished) {
                   createLighthing(player);
                   jPunished = true;
               }
            });
            counter = 0;
        } else counter++;
    }

    private void spawnGuardians(World world, PlayerEntity player) {
        int rand = RANDOM.nextInt(1, 10);
        Random random = Random.create();
        for (BlockPos pos : BlockPos.iterateRandomly(random, rand, player.getBlockPos(), 5)) {
            EntityType.GUARDIAN.spawn((ServerWorld) world, pos, SpawnReason.EVENT);
        }
    }

    private void createLighthing(PlayerEntity player) {
        int rand = RANDOM.nextInt(2, 5);
        Random random = Random.create();
        EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), player.getBlockPos(), SpawnReason.EVENT);
        for (BlockPos pos : BlockPos.iterateRandomly(random, rand, player.getBlockPos(), 5)) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), pos, SpawnReason.EVENT);
        }
    }
}
