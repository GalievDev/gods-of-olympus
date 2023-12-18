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

    public static short getRepNeptune(IPlayerDataSaver player) {
        NbtCompound nbt = player.getPersistentData();
        return nbt.getShort("neptune_rep");
    }
}
