package dev.galiev.gofo.client.render;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static dev.galiev.gofo.GodsOfOlympus.MOD_ID;

public class PoseidonTridentItemRenderer {
    public static void render(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.scale(1.0f, -1.0f, -1.0f);
        TridentEntityModel model = new TridentEntityModel(TridentEntityModel.getTexturedModelData().createModel());
        model.render(matrices, ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                model.getLayer(new Identifier(MOD_ID, "textures/entity/poseidon_trident.png")),
                false,
                stack.hasGlint()),
                light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);

        matrices.pop();
    }
}
