package io.github.coffeecatrailway.food.common.item.crafting;

import com.mojang.datafixers.util.Pair;
import io.github.coffeecatrailway.food.common.item.FoodComboItem;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 31/05/2025
 */
public class SandwichRecipe extends CustomRecipe
{
	public SandwichRecipe(CraftingBookCategory category)
	{
		super(category);
	}

	@Override
	public boolean matches(CraftingInput input, Level level)
	{
		int maxBunCount = ModItems.SANDWICH.get().foodComboProperties.isHasTwoBuns() ? 2 : 1;
		int bunCount = 0;
		int ingredientCount = 0;

		Pair<Integer, Integer> checkSlots = this.checkSlots(input);
		for (int i = checkSlots.getFirst(); i < checkSlots.getSecond(); i++)
		{
			ItemStack stack = input.getItem(i);
			if (stack.isEmpty())
				continue;
			if (stack.getFoodProperties(null) == null || stack.getItem() instanceof FoodComboItem)
				return false;

			if (stack.is(ModItems.BREAD_SLICE))
				bunCount++;
			else
				ingredientCount++;
		}
		return bunCount == maxBunCount && ingredientCount > 0;
	}

	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries)
	{
		ItemStack sandwichStack = new ItemStack(ModItems.SANDWICH.get(), 1);
		List<ItemStack> ingredients = new ArrayList<>();

		Pair<Integer, Integer> checkSlots = this.checkSlots(input);
		for (int i = checkSlots.getFirst(); i < checkSlots.getSecond(); i++)
		{
			ItemStack stack = input.getItem(i);
			if (stack.isEmpty() || stack.getFoodProperties(null) == null || stack.is(ModItems.BREAD_SLICE))
				continue;
			ingredients.add(stack.copy());
		}
		if (!ingredients.isEmpty())
			sandwichStack.set(ModComponents.FOOD_COMBO.get(), new FoodComboComponent(ingredients, false));
		return sandwichStack;
	}

	protected Pair<Integer, Integer> checkSlots(CraftingInput input)
	{
		return Pair.of(0, input.size());
	}

	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return width >= 2 && height >= 2;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.SANDWICH_SERIALIZER.get();
	}
}
