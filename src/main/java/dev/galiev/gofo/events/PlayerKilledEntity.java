package dev.galiev.gofo.events;

import dev.galiev.gofo.events.custom.PlayerKilledEntityCallback;
import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PlayerKilledEntity implements PlayerKilledEntityCallback {
    @Override
    public void killEntity(ServerPlayerEntity player, Entity entity) {
        if (entity instanceof ZombieEntity) {
            GodsData.addRepNeptune((IPlayerDataSaver) player, (short) 1);
        } else if (entity instanceof HorseEntity || entity instanceof DolphinEntity || entity instanceof TurtleEntity) {
            GodsData.removeRepNeptune((IPlayerDataSaver) player, (short) 1);
            if (GodsData.getRepNeptune((IPlayerDataSaver) player) <= 5) {
                player.sendMessage(Text.literal("Neptune dissatisfied with your actions").copy().formatted(Formatting.DARK_RED), true);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            }
        }
        player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        //player.sendMessage(Text.of("Neptune Reputation: " + GodsData.getRepNeptune((IPlayerDataSaver) player)), true);
    }
}
