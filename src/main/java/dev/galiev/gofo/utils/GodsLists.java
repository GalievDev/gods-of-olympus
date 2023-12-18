package dev.galiev.gofo.utils;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

//Move its to nbt tags
public class GodsLists {
    private static final List<PlayerEntity> neptuneBlackList = new ArrayList<>();
    private static final List<PlayerEntity> jupiterBlackList = new ArrayList<>();

    public static List<PlayerEntity> getNeptuneBlackList() {
        return neptuneBlackList;
    }

    public static List<PlayerEntity> getJupiterBlackList() {
        return jupiterBlackList;
    }

    public static void addPlayerToNeptune(PlayerEntity player) {
        neptuneBlackList.add(player);
    }

    public static void addPlayerToJupiter(PlayerEntity player) {
        jupiterBlackList.add(player);
    }
}
