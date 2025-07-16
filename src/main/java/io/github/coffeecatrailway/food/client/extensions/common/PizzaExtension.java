package io.github.coffeecatrailway.food.client.extensions.common;

import io.github.coffeecatrailway.food.client.renderer.PizzaRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

/**
 * @author CoffeeCatRailway
 * Created: 16/07/2025
 */
public class PizzaExtension implements IClientItemExtensions
{
	@Override
	public BlockEntityWithoutLevelRenderer getCustomRenderer()
	{
		return PizzaRenderer.INSTANCE;
	}
}
