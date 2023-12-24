package dev.galiev.gofo.client.render;

import dev.galiev.gofo.entity.NeptuneTridentEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public class NeptuneTridentEntityRenderer extends EntityRenderer<NeptuneTridentEntity> {
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/neptune_trident.png");

    public NeptuneTridentEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    private TridentEntityModel model = new TridentEntityModel(TridentEntityModel.getTexturedModelData().createModel());

    @Override
    public void render(NeptuneTridentEntity tridentEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        matrixStack.push();
        matrixStack.multiply(
                RotationAxis.POSITIVE_Y.rotationDegrees(
                        MathHelper.lerp(
                                tickDelta,
                                tridentEntity.prevYaw,
                                tridentEntity.getYaw()
                        ) - 90.0f
                )
        );
        matrixStack.multiply(
                RotationAxis.POSITIVE_Z.rotationDegrees(
                        MathHelper.lerp(
                                tickDelta,
                                tridentEntity.prevPitch,
                                tridentEntity.getPitch()
                        ) + 90.0f
                )
        );
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider,
                model.getLayer(getTexture(tridentEntity)),
                false,
                false
        );
        model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(tridentEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
    }



    @Override
    public Identifier getTexture(NeptuneTridentEntity entity) {
        return TEXTURE;
    }
}
