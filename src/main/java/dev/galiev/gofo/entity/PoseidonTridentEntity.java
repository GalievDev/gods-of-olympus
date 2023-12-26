package dev.galiev.gofo.entity;

import dev.galiev.gofo.registry.EntitiesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PoseidonTridentEntity extends PersistentProjectileEntity {
    private static final ItemStack DEFAULT_STACK = new ItemStack(Items.TRIDENT);
    private boolean dealtDamage;
    public int returnTimer;

    public PoseidonTridentEntity(EntityType<? extends PoseidonTridentEntity> type, World world) {
        super(type, world, DEFAULT_STACK);
    }

    public PoseidonTridentEntity(LivingEntity owner, World world, ItemStack stack) {
        super(EntitiesRegistry.POSEIDON_TRIDENT_ENTITY, owner, world, stack);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        var owner = this.getOwner();
        if ((this.dealtDamage || this.isNoClip()) && owner != null) {
            if (!owner.isAlive()) {
                if (!this.getWorld().isClient && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1f);
                }
                this.discard();
            } else {
                this.setNoClip(true);
                Vec3d vec3d = owner.getEyePos().subtract(this.getPos());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015 * (double)2, this.getZ());
                if (this.getWorld().isClient) {
                    this.lastRenderY = this.getY();
                }
                double d = 0.05 * (double)2;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0f, 1.0f);
                }
                ++this.returnTimer;
            }
        }
        super.tick();
    }
}
