package dev.galiev.gofo.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
    public static short addRepNeptune(IPlayerDataSaver player, short amount) {
        NbtCompound nbt = player.getPersistentData();
        short rep = nbt.getShort("neptune_rep");
        if (rep + amount >= 15) {
            rep = 15;
        } else {
            rep += amount;
        }

        nbt.putShort("neptune_rep", rep);
        return rep;
    }

    public static short addRepJupiter(IPlayerDataSaver player, short amount) {
        NbtCompound nbt = player.getPersistentData();
        short rep = nbt.getShort("jupiter_rep");
        if (rep + amount >= 15) {
            rep = 15;
        } else {
            rep += amount;
        }

        nbt.putShort("jupiter_rep", rep);
        return rep;
    }

    public static short removeRepNeptune(IPlayerDataSaver player, short amount) {
        NbtCompound nbt = player.getPersistentData();
        short rep = nbt.getShort("neptune_rep");
        if (rep - amount < 0) {
            rep = 0;
        } else {
            rep -= amount;
        }

        nbt.putShort("neptune_rep", rep);
        return rep;
    }

    public static short removeRepJupiter(IPlayerDataSaver player, short amount) {
        NbtCompound nbt = player.getPersistentData();
        short rep = nbt.getShort("jupiter_rep");
        if (rep - amount < 0) {
            rep = 0;
        } else {
            rep -= amount;
        }

        nbt.putShort("jupiter_rep", rep);
        return rep;
    }

    public static short getRepNeptune(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("neptune_rep");
    }

    public static short getRepJupiter(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("jupiter_rep");
    }

    public static boolean isNeptuneHate(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("neptune_rep") <= 5;
    }

    public static boolean isJupiterHate(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("jupiter_rep") <= 5;
    }

    public static boolean isNeptuneLike(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("neptune_rep") >= 12;
    }

    public static boolean isJupiterLike(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("jupiter_rep") >= 12;
    }

    @Environment(EnvType.CLIENT)
    public static void info(PlayerEntity player) {
        if (getRepNeptune((IPlayerDataSaver) player) == 5) {
            player.sendMessage(Text.literal("Neptune dissatisfied with your actions").formatted(Formatting.DARK_RED), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        }
        if (getRepNeptune((IPlayerDataSaver) player) == 12) {
            player.sendMessage(Text.literal("Neptune respects your actions").formatted(Formatting.AQUA), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1000));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1f, 1f);
        }
        if (getRepJupiter((IPlayerDataSaver) player) == 5) {
            player.sendMessage(Text.literal("Jupiter dissatisfied with your actions").formatted(Formatting.DARK_RED), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) player.getWorld(), player.getBlockPos(), SpawnReason.EVENT);
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT, SoundCategory.NEUTRAL, 1f, 1f);
        }
        if (getRepJupiter((IPlayerDataSaver) player) == 12) {
            player.sendMessage(Text.literal("Neptune respects your actions").formatted(Formatting.YELLOW), true);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1000));
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1f, 1f);
        }
    }
}
