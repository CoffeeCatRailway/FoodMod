package io.github.coffeecatrailway.food.common.item.crafting;

import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.datagen.ModItemTags;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 16/07/2025
 */
public class PizzaRecipe extends FoodComboRecipe
{
	public PizzaRecipe(CraftingBookCategory category)
	{
		super(category, ModItemTags.FOODS_UNFIRED_PIZZA_BASE, ModItems.PIZZA, Map.of(ModItemTags.FOODS_TOMATO_SAUCE, 1));
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.PIZZA_SERIALIZER.get();
	}
}
