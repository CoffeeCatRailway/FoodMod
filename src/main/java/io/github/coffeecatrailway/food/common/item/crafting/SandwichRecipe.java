package io.github.coffeecatrailway.food.common.item.crafting;

import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.datagen.ModItemTags;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;

/**
 * @author CoffeeCatRailway
 * Created: 31/05/2025
 */
public class SandwichRecipe extends FoodComboRecipe
{
	public SandwichRecipe(CraftingBookCategory category)
	{
		super(category, ModItemTags.FOODS_BREAD_SLICE, ModItems.SANDWICH);
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.SANDWICH_SERIALIZER.get();
	}
}
