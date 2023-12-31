package dev.galiev.gofo.utils;

import dev.galiev.gofo.config.ConfigManager;
import dev.galiev.gofo.registry.EffectsRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class GodsData {
    public static short addRepPoseidon(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("poseidon_rep");
        if (rep + amount >= 15) {
            rep = 15;
        } else {
            rep += amount;
        }

        if (rep == 12 && ConfigManager.read().poseidonEvents()) {
            player.sendMessage(Text.literal("Poseidon respects your actions").formatted(Formatting.AQUA), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1000));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1f, 1f);
        } else {
            player.sendMessage(Text.of("Respect from Poseidon: " + rep), true);
        }

        nbt.putShort("poseidon_rep", rep);
        return rep;
    }

    public static short addRepZeus(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("zeus_rep");
        if (rep + amount >= 15) {
            rep = 15;
        } else {
            rep += amount;
        }

        if (rep == 12 && ConfigManager.read().zeusEvents()) {
            player.sendMessage(Text.literal("Zeus respects your actions").formatted(Formatting.YELLOW), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1000));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1f, 1f);
        }  else {
            player.sendMessage(Text.of("Respect from Zeus: " + rep), true);
        }

        nbt.putShort("zeus_rep", rep);
        return rep;
    }

    public static short removeRepPoseidon(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("poseidon_rep");
        if (rep - amount < 0) {
            rep = 0;
        } else {
            rep -= amount;
        }

        if (rep == 5 && ConfigManager.read().poseidonEvents()) {
            player.sendMessage(Text.literal("Poseidon dissatisfied with your actions").formatted(Formatting.DARK_RED), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            player.addStatusEffect(new StatusEffectInstance(EffectsRegistry.WATER_SUFFOCATION, 60));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        } else {
            player.sendMessage(Text.of("Respect from Poseidon: " + rep), true);
        }

        nbt.putShort("poseidon_rep", rep);
        return rep;
    }

    public static short removeRepZeus(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("zeus_rep");
        if (rep - amount < 0) {
            rep = 0;
        } else {
            rep -= amount;
        }

        if (rep == 5 && ConfigManager.read().zeusEvents()) {
            player.sendMessage(Text.literal("Zeus dissatisfied with your actions").formatted(Formatting.DARK_RED), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), player.getBlockPos(), SpawnReason.EVENT);
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        } else {
            player.sendMessage(Text.of("Respect from Zeus: " + rep), true);
        }

        nbt.putShort("zeus_rep", rep);
        return rep;
    }

    public static short getRepPoseidon(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("poseidon_rep");
    }

    public static short getRepZeus(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("zeus_rep");
    }

    public static boolean isPoseidonHate(PlayerEntity player) {
        return getRepPoseidon(player) <= 5;
    }

    public static boolean isZeusHate(PlayerEntity player) {
        return getRepZeus(player) <= 5;
    }

    public static boolean isPoseidonLike(PlayerEntity player) {
        return getRepPoseidon(player) >= 12;
    }

    public static boolean isZeusLike(PlayerEntity player) {
        return getRepZeus(player) >= 12;
    }
}
