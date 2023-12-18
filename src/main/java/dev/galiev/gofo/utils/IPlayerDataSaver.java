package dev.galiev.gofo.utils;

import net.minecraft.nbt.NbtCompound;

public interface IPlayerDataSaver {
    NbtCompound getPersistentData();
}
