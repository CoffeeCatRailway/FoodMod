package io.github.coffeecatrailway.food.client.extensions.common;

import io.github.coffeecatrailway.food.client.renderer.FoodComboStackedRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

/**
 * @author CoffeeCatRailway
 * Created: 02/07/2025
 */
public class FoodComboStackedExtension implements IClientItemExtensions
{
	@Override
	public BlockEntityWithoutLevelRenderer getCustomRenderer()
	{
		return FoodComboStackedRenderer.INSTANCE;
	}
}
