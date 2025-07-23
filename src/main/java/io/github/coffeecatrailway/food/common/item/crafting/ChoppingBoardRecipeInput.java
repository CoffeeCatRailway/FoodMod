package io.github.coffeecatrailway.food.common.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

/**
 * @author CoffeeCatRailway
 * Created: 22/07/2025
 */
public record ChoppingBoardRecipeInput(ItemStack ingredient, ItemStack tool) implements RecipeInput
{
	@Override
	public ItemStack getItem(int index)
	{
		return switch (index)
		{
			case 0 -> this.ingredient;
			case 1 -> this.tool;
			default -> throw new IllegalArgumentException("Recipe does not contain slot " + index);
		};
	}

	@Override
	public int size()
	{
		return 2;
	}

	@Override
	public boolean isEmpty()
	{
		return this.ingredient.isEmpty() && this.tool.isEmpty();
	}
}
