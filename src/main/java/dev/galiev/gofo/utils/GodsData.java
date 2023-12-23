package dev.galiev.gofo.utils;

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
    public static short addRepNeptune(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("neptune_rep");
        if (rep + amount >= 15) {
            rep = 15;
        } else {
            rep += amount;
        }

        if (rep == 12) {
            player.sendMessage(Text.literal("Neptune respects your actions").formatted(Formatting.AQUA), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1000));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1f, 1f);
        }

        nbt.putShort("neptune_rep", rep);
        return rep;
    }

    public static short addRepJupiter(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("jupiter_rep");
        if (rep + amount >= 15) {
            rep = 15;
        } else {
            rep += amount;
        }

        if (rep == 12) {
            player.sendMessage(Text.literal("Neptune respects your actions").formatted(Formatting.YELLOW), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1000));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1f, 1f);
        }

        nbt.putShort("jupiter_rep", rep);
        return rep;
    }

    public static short removeRepNeptune(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("neptune_rep");
        if (rep - amount < 0) {
            rep = 0;
        } else {
            rep -= amount;
        }

        if (rep == 5) {
            player.sendMessage(Text.literal("Neptune dissatisfied with your actions").formatted(Formatting.DARK_RED), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        }

        nbt.putShort("neptune_rep", rep);
        return rep;
    }

    public static short removeRepJupiter(PlayerEntity player, short amount) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        short rep = nbt.getShort("jupiter_rep");
        if (rep - amount < 0) {
            rep = 0;
        } else {
            rep -= amount;
        }

        if (rep == 5) {
            player.sendMessage(Text.literal("Jupiter dissatisfied with your actions").formatted(Formatting.DARK_RED), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), player.getBlockPos(), SpawnReason.EVENT);
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        }

        nbt.putShort("jupiter_rep", rep);
        return rep;
    }

    public static short getRepNeptune(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("neptune_rep");
    }

    public static short getRepJupiter(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("jupiter_rep");
    }

    public static boolean isNeptuneHate(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("neptune_rep") <= 5;
    }

    public static boolean isJupiterHate(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("jupiter_rep") <= 5;
    }

    public static boolean isNeptuneLike(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("neptune_rep") >= 12;
    }

    public static boolean isJupiterLike(PlayerEntity player) {
        NbtCompound nbt = ((IPlayerDataSaver) player).getPersistentData();
        return nbt.getShort("jupiter_rep") >= 12;
    }
}
