package dev.galiev.gofo.events;

import dev.galiev.gofo.utils.GodsLists;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;

public class ServerLifeCycle implements ServerTickEvents.StartWorldTick{
    @Override
    public void onStartTick(ServerWorld world) {
        if (!GodsLists.getNeptuneBlackList().isEmpty()) {

        }
    }
}
