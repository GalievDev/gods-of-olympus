package dev.galiev.gofo.utils;

import net.minecraft.nbt.NbtCompound;

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
}
