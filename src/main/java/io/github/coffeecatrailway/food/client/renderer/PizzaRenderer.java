package io.github.coffeecatrailway.food.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.item.FoodComboItem;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 16/07/2025
 */
public class PizzaRenderer extends BlockEntityWithoutLevelRenderer
{
	public static final PizzaRenderer INSTANCE = new PizzaRenderer();
	private static final Random RANDOM = new Random(42);

	private static final ResourceLocation PIZZA_SAUCE = FoodMod.id("textures/item/pizza_sauce.png");

	public PizzaRenderer()
	{
		super(null, null);
	}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager)
	{
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay)
	{
		poseStack.pushPose();
		poseStack.translate(.5f, .5f, .5f);

		if (displayContext == ItemDisplayContext.GROUND)
			poseStack.mulPose(Axis.XN.rotationDegrees(90));
		if (displayContext.firstPerson())
		{
			poseStack.mulPose(Axis.XN.rotationDegrees(90));
			poseStack.mulPose(Axis.YN.rotationDegrees(20));
		}

		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);
		FoodComboItem.FoodComboProperties properties = ((FoodComboItem) stack.getItem()).foodComboProperties;
		ItemStack bun = properties.getBunItem(data.toasted());

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		RANDOM.setSeed(42);

		// Render bun
//		poseStack.translate(0f, 0f,  -(.06f * data.ingredients().size() / 12f) / 2f);
		itemRenderer.renderStatic(bun, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, null, 0);

		// Render sauce
		poseStack.pushPose();
		poseStack.translate(0f, 0f, .0321f);

		PoseStack.Pose last = poseStack.last();
		VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(PIZZA_SAUCE));

		addSauceVertex(consumer, last, .5f, .5f, 0f, 0f, 0f, packedLight);
		addSauceVertex(consumer, last, -.5f, .5f, 0f, 1f, 0f, packedLight);
		addSauceVertex(consumer, last, -.5f, -.5f, 0f, 1f, 1f, packedLight);
		addSauceVertex(consumer, last, .5f, -.5f, 0f, 0f, 1f, packedLight);
		poseStack.popPose();

		// Render ingredients
		float scale = .2f;
		poseStack.scale(scale, scale, scale);
		poseStack.translate(0f, 0f, .18f);
		for (ItemStack ingredient : data.ingredients())
		{
			poseStack.pushPose();
			float angle = RANDOM.nextFloat(Mth.TWO_PI * 1.5f);
			float length = RANDOM.nextFloat() * .9f + .5f;

			float x = Mth.sin(angle) * length;
			float y = Mth.cos(angle) * length;
			float z = RANDOM.nextFloat() * 2f - 1f;
			z *= .01f;

			poseStack.translate(x, y, z);

			float rotation = RANDOM.nextFloat() * Mth.TWO_PI;
			if (FoodConfigs.CLIENT.rotateIngredients.getAsBoolean())
				poseStack.mulPose(Axis.ZN.rotation(rotation));

			itemRenderer.renderStatic(ingredient, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, null, 0);

			poseStack.popPose();
		}

		poseStack.popPose();
	}

	private void addSauceVertex(VertexConsumer consumer, PoseStack.Pose last, float x, float y, float z, float u, float v, int packedLight)
	{
		Vector3f pos = last.pose().transformPosition(x, y, z, new Vector3f());
		Vector3f normal = last.normal().transform(new Vector3f(1f));
		consumer.addVertex(pos.x, pos.y, pos.z, FastColor.ARGB32.colorFromFloat(1f, 1f, 1f, 1f), u, v, OverlayTexture.NO_OVERLAY, packedLight, normal.x, normal.y, normal.z);
	}
}
