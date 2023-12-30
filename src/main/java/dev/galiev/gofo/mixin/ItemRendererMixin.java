package dev.galiev.gofo.mixin;

import dev.galiev.gofo.client.render.PoseidonTridentItemRenderer;
import dev.galiev.gofo.registry.ItemsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Mutable
    @Final
    @Shadow
    private final ItemModels models;

    public ItemRendererMixin(BakedModelManager baker) {
        this.models = new ItemModels(baker);
    }
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useRubyStaffModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ItemsRegistry.ZEUS_LIGHTNING) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).gofo$getModels().getModelManager().getModel(new ModelIdentifier(MOD_ID, "zeus_lightning_3d", "inventory"));
        }
        return value;
    }

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), cancellable = true)
    public void renderItem(ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if (!stack.isEmpty() && stack.getItem() == ItemsRegistry.POSEIDON_TRIDENT) {
            matrices.push();
            boolean bl = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;
            if (stack.getItem() == ItemsRegistry.POSEIDON_TRIDENT && bl) {
                model = ((ItemRendererAccessor) this).gofo$getModels().getModelManager().getModel(new ModelIdentifier(MOD_ID, "poseidon_trident", "inventory"));
            }

            model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            if (model.isBuiltin() || stack.getItem() == ItemsRegistry.POSEIDON_TRIDENT && !bl) {
                PoseidonTridentItemRenderer.render(stack, matrices, vertexConsumers, light, overlay);
            } else {
                RenderLayer renderLayer = RenderLayers.getItemLayer(stack, true);
                VertexConsumer vertexConsumer4;
                vertexConsumer4 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, renderLayer, true, stack.hasGlint());

                ((ItemRendererAccessor) this).renderBakedItemModelInvoker(model, stack, light, overlay, matrices, vertexConsumer4);
            }

            matrices.pop();
            ci.cancel();
        }
    }

    @Inject(method = "getModel", at = @At(value = "HEAD"), cancellable = true)
    public void getHeldItemModelMixin(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> info) {
        if (stack.getItem() == ItemsRegistry.POSEIDON_TRIDENT) {
            BakedModel bakedModel2 = this.models.getModelManager().getModel(ItemRenderer.TRIDENT_IN_HAND);
            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld) world : null;
            BakedModel bakedModel3 = bakedModel2.getOverrides().apply(bakedModel2, stack, clientWorld, entity, seed);
            info.setReturnValue(bakedModel3 == null ? this.models.getModelManager().getMissingModel() : bakedModel3);
        }

    }
}
