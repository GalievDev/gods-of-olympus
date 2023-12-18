package dev.galiev.gofo.events;

import dev.galiev.gofo.registry.EffectsRegistry;
import dev.galiev.gofo.utils.GodsLists;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

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
            if (!GodsLists.getNeptuneBlackList().isEmpty() && !punished) {
                for (PlayerEntity player : GodsLists.getNeptuneBlackList()) {
                    player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 400));
                    punished = true;
                }
            }
            counter = 0;
        } else counter++;
    }
}
