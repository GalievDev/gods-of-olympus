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
import net.minecraft.text.Text;

public class PlayerKilledEntity implements PlayerKilledEntityCallback {
    @Override
    public void killEntity(ServerPlayerEntity player, Entity entity) {
        if (entity instanceof ZombieEntity) {
            GodsData.addRepNeptune(player, (short) 1);
        } else if (entity instanceof HorseEntity || entity instanceof DolphinEntity || entity instanceof TurtleEntity) {
            GodsData.removeRepNeptune(player, (short) 1);
        }

        if (entity instanceof PhantomEntity || (entity instanceof SheepEntity && isEntityOnHills(player)) || entity instanceof RaiderEntity) {
            GodsData.addRepJupiter(player, (short) 1);
        } else if (entity instanceof ChickenEntity) {
            GodsData.removeRepJupiter(player, (short) 1);
        }

        player.sendMessage(Text.of("Reputation: " + GodsData.getRepNeptune(player)));
    }

    private boolean isEntityOnHills(Entity entity) {
        return entity.getWorld().getBiome(entity.getBlockPos()).isIn(BiomeTags.IS_HILL);
    }
}
