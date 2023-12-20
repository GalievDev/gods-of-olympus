package dev.galiev.gofo.events;

import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
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
            world.getPlayers().forEach (player -> {
               if (GodsData.isNeptuneHate((IPlayerDataSaver) player) && !punished) {
                   //player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 350));
                   punished = true;
               }
            });
            counter = 0;
        } else counter++;
    }
}
