package dev.galiev.gofo.mixin;

import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberMixin {
    @Shadow @Nullable public abstract PlayerEntity getPlayerOwner();

    @ModifyVariable(method = "use", at = @At(value = "STORE"), ordinal = 0)
    private List<ItemStack> changeCatch(List<ItemStack> list) {
        var player = this.getPlayerOwner();
        if (GodsData.isNeptuneHate((IPlayerDataSaver) player)) {
            ObjectArrayList<ItemStack> newList = new ObjectArrayList<>();
            newList.add(new ItemStack(Items.PUFFERFISH));
            return newList;
        } else {
        return list;
        }
    }
}
