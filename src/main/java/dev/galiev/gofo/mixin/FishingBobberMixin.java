package dev.galiev.gofo.mixin;

import dev.galiev.gofo.utils.GodsData;
import dev.galiev.gofo.utils.IPlayerDataSaver;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberMixin {

    @Shadow @Nullable public abstract PlayerEntity getPlayerOwner();

    @Shadow private int hookCountdown;

    @Inject(method = "use", at = @At(value = "TAIL"))
    private void canFish(ItemStack usedItem, CallbackInfoReturnable<Integer> cir) {
        var player = this.getPlayerOwner();
        if (GodsData.isNeptuneHate((IPlayerDataSaver) player)) {
            if (this.hookCountdown > 0) {
                ItemEntity entity = new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(), new ItemStack(Items.PUFFERFISH));
                player.getWorld().spawnEntity(entity);
            }
        }
    }
}
