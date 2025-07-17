package io.github.coffeecatrailway.food.common.item.crafting;

import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.datagen.ModItemTags;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;

/**
 * @author CoffeeCatRailway
 * Created: 16/07/2025
 */
public class CrackerRecipe extends FoodComboRecipe
{
	public CrackerRecipe(CraftingBookCategory category)
	{
		super(category, ModItemTags.FOODS_CRACKER, ModItems.CRACKER);
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.CRACKER_SERIALIZER.get();
	}
}
