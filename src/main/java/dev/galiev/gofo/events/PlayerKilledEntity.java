package dev.galiev.gofo.events;

import dev.galiev.gofo.events.custom.PlayerKilledEntityCallback;
import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class PlayerKilledEntity implements PlayerKilledEntityCallback {
    @Override
    public void killEntity(ServerPlayerEntity player, Entity entity) {
        if (entity instanceof ZombieEntity) {
            GodsData.addRepNeptune((IPlayerDataSaver) player, (short) 1);
            GodsData.info(player);
        } else if (entity instanceof HorseEntity || entity instanceof DolphinEntity || entity instanceof TurtleEntity) {
            GodsData.removeRepNeptune((IPlayerDataSaver) player, (short) 1);
            GodsData.info(player);
        }

        if (entity instanceof PhantomEntity || (entity instanceof SheepEntity && isEntityOnHills(player))) {
            GodsData.addRepJupiter((IPlayerDataSaver) player, (short) 1);
            GodsData.info(player);
        } else if (entity instanceof ChickenEntity) {
            GodsData.removeRepJupiter((IPlayerDataSaver) player, (short) 1);
            GodsData.info(player);
        }

        player.sendMessage(Text.of("Reputation: " + GodsData.getRepNeptune((IPlayerDataSaver) player)));
    }

    private boolean isEntityOnHills(Entity entity) {
        return entity.getWorld().getBiome(entity.getBlockPos()).isIn(BiomeTags.IS_HILL);
    }
}
