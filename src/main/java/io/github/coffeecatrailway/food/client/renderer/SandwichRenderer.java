package io.github.coffeecatrailway.food.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.coffeecatrailway.food.common.item.FoodComboItem;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

/**
 * @author CoffeeCatRailway
 * Created: 02/07/2025
 */
public class SandwichRenderer extends BlockEntityWithoutLevelRenderer
{
	public static final SandwichRenderer INSTANCE = new SandwichRenderer();
	private static final Random RANDOM = new Random(42);

	public SandwichRenderer()
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
		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);
		if (data.ingredients().isEmpty())
			return;

		poseStack.pushPose();

		poseStack.translate(.5f, .5f, .5f);

		FoodComboItem.FoodComboProperties properties = ((FoodComboItem) stack.getItem()).foodComboProperties;
		ItemStack bun = properties.getBunItem(data.toasted());

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		RANDOM.setSeed(42);

		// Render bun
		poseStack.translate(0f, 0f, -(.06f * data.ingredients().size()) / 2f);
		itemRenderer.renderStatic(bun, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, null, 0);

		// Render ingredients
		data.ingredients().forEach(ingredient -> {
			poseStack.translate(0.f, 0.f, .06f);

			float angle = (float) (RANDOM.nextFloat() * Math.PI * 2f);
			if (FoodConfigs.CLIENT.rotateIngredients.getAsBoolean())
				poseStack.mulPose(Axis.ZN.rotation(angle));

			itemRenderer.renderStatic(ingredient, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, null, 0);

			if (FoodConfigs.CLIENT.rotateIngredients.getAsBoolean())
				poseStack.mulPose(Axis.ZP.rotation(angle));
		});

		// Render other bun
		if (properties.isHasTwoBuns())
		{
			poseStack.translate(0f, 0f, .06f);
			itemRenderer.renderStatic(bun, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, buffer, null, 0);
		}
		poseStack.popPose();
	}
}
