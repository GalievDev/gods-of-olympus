package dev.galiev.gofo.events;

import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Random;

public class ServerLifeCycle implements ServerTickEvents.StartWorldTick {
    private static final int delay = 400;
    private static int counter = 0;
    private static boolean punished = false;

    @Override
    public void onStartTick(ServerWorld world) {
        int pDelay = 300;
        if (counter == pDelay) punished = false;
        if (counter < 0) counter = 0;

        if (counter == delay) {
            world.getPlayers().forEach (player -> {
               if (GodsData.isNeptuneHate((IPlayerDataSaver) player) && !punished) {
                   //player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 350));
                   teleportToOcean(player, world);
                   punished = true;
               }
            });
            counter = 0;
        } else counter++;
    }

    public void teleportToOcean(PlayerEntity player, ServerWorld world) {
/*        var biomes = world.getChunkManager().getChunkGenerator().getBiomeSource().getBiomesInArea(player.getBlockX(), player.getBlockY(), player.getBlockZ(),
                10000, world.getChunkManager().getNoiseConfig().getMultiNoiseSampler());
        for (RegistryEntry<Biome> biome : biomes ) {
            if (biome.getKey().isPresent()) {
                if (biome.getKey().get().getValue().toString().equals(BiomeKeys.OCEAN.getValue().toString())) {
                    int i = Math.floorDiv(10000, 32);
                    int[] is = MathHelper.stream((int) player.getY(), world.getBottomY() + 1, world.getTopY(), 64).toArray();
                    for (BlockPos.Mutable mutable : BlockPos.iterateInSquare(BlockPos.ORIGIN, i, Direction.EAST, Direction.SOUTH)) {
                        int j = (int) (player.getX() + mutable.getX() * 32);
                        int k = (int) (player.getZ() + mutable.getZ() * 32);
                        int l = BiomeCoords.fromBlock(j);
                        int m = BiomeCoords.fromBlock(k);
                        for (int n : is) {
                            int o = BiomeCoords.fromBlock(n);
                            RegistryEntry<Biome> registryEntry = world.getBiome(mutable);
                            if (!biomes.contains(registryEntry)) continue;

                            player.teleport(mutable.getX(), mutable.getY(), mutable.getZ());
                        }
                    }
                }
            }
        }*/
        Random random = new Random();
        if (world.getBiome(player.getBlockPos()) != BiomeKeys.OCEAN) {

        }
    }
}
