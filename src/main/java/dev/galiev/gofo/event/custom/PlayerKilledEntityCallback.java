package dev.galiev.gofo.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerKilledEntityCallback {
    Event<PlayerKilledEntityCallback> EVENT = EventFactory.createArrayBacked(PlayerKilledEntityCallback.class, (listeners) -> (player, killedEntity) -> {
        for (PlayerKilledEntityCallback listener : listeners) {
            listener.killEntity(player, killedEntity);
        }
    });

    void killEntity(ServerPlayerEntity player, Entity killedEntity);
}
