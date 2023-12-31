package dev.galiev.gofo.event;

import dev.galiev.gofo.event.custom.PlayerKilledEntityCallback;
import dev.galiev.gofo.utils.GodsData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.network.ServerPlayerEntity;

import static dev.galiev.gofo.GodsOfOlympus.RANDOM;

public class PlayerKilledEntity implements PlayerKilledEntityCallback {
    @Override
    public void killEntity(ServerPlayerEntity player, Entity entity) {
        int chance = RANDOM.nextInt(1, 100);
        if (chance <= 19) {
            if (entity instanceof ZombieEntity) {
                GodsData.addRepPoseidon(player, (short) 1);
            } else if (entity instanceof HorseEntity || entity instanceof DolphinEntity || entity instanceof TurtleEntity) {
                GodsData.removeRepPoseidon(player, (short) 1);
            }

            if (entity instanceof PhantomEntity || (entity instanceof SheepEntity && isEntityOnHills(player)) || entity instanceof RaiderEntity) {
                GodsData.addRepZeus(player, (short) 1);
            } else if (entity instanceof ChickenEntity || entity instanceof VillagerEntity || entity instanceof IronGolemEntity) {
                GodsData.removeRepZeus(player, (short) 1);
            }
        }
    }

    private boolean isEntityOnHills(Entity entity) {
        return entity.getWorld().getBiome(entity.getBlockPos()).isIn(BiomeTags.IS_HILL);
    }
}
