package io.github.coffeecatrailway.food.common.item.crafting;

import com.mojang.datafixers.util.Pair;
import io.github.coffeecatrailway.food.common.item.FoodComboItem;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 16/07/2025
 */
public abstract class FoodComboRecipe extends CustomRecipe
{
	private final TagKey<Item> bunTag;
	private final Supplier<? extends FoodComboItem> foodComboItem;
	private final Map<TagKey<Item>, Integer> extraIngredients;

	public FoodComboRecipe(CraftingBookCategory category, TagKey<Item> bunTag, Supplier<? extends FoodComboItem> foodComboItem)
	{
		this(category, bunTag, foodComboItem, new HashMap<>());
	}

	public FoodComboRecipe(CraftingBookCategory category, TagKey<Item> bunTag, Supplier<? extends FoodComboItem> foodComboItem, Map<TagKey<Item>, Integer> extraIngredients)
	{
		super(category);
		this.bunTag = bunTag;
		this.foodComboItem = foodComboItem;
		this.extraIngredients = extraIngredients;
	}

	@Override
	public boolean matches(CraftingInput input, Level level)
	{
		int maxBunCount = this.bunCount();
		int bunCount = 0;
		int ingredientCount = 0;

		Map<Item, Integer> foundExtraIngredients = new HashMap<>();
		for (int i = 0; i < input.size(); i++)
		{
			ItemStack stack = input.getItem(i);
			if (stack.isEmpty())
				continue;
			if (stack.getFoodProperties(null) == null)// || stack.getItem() instanceof FoodComboItem
				return false;

			if (this.extraIngredients.keySet().stream().anyMatch(stack::is))
			{
				Item item = stack.getItem();
				if (foundExtraIngredients.containsKey(item))
					foundExtraIngredients.replace(item, foundExtraIngredients.get(item) + 1);
				else
					foundExtraIngredients.put(item, 1);
			} else if (stack.is(this.bunTag))
				bunCount++;
			else
				ingredientCount++;
		}

		int expectedExtra = this.extraIngredients.values().stream().reduce(0, Integer::sum);
		int foundExtra = foundExtraIngredients.values().stream().reduce(0, Integer::sum);
		boolean hasExtraIngredients = expectedExtra == foundExtra; // TODO: Find better way of verifying extra ingredients

		return bunCount == maxBunCount && ingredientCount > 0 && hasExtraIngredients;
	}

	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries)
	{
		ItemStack sandwichStack = new ItemStack(this.foodComboItem.get(), 1);
		List<ItemStack> ingredients = new ArrayList<>();

		for (int i = 0; i < input.size(); i++)
		{
			ItemStack stack = input.getItem(i);
			if (stack.isEmpty() || stack.getFoodProperties(null) == null || stack.is(this.bunTag) || this.extraIngredients.keySet().stream().anyMatch(stack::is))
				continue;
			ingredients.add(stack.copy());
		}
		if (!ingredients.isEmpty())
			sandwichStack.set(ModComponents.FOOD_COMBO.get(), new FoodComboComponent(ingredients, false));
		return sandwichStack;
	}

	private int bunCount()
	{
		return this.foodComboItem.get().foodComboProperties.isHasTwoBuns() ? 2 : 1;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		int min = this.bunCount() + 1;
		if (!this.extraIngredients.isEmpty())
			min += this.extraIngredients.values().stream().reduce(0, Integer::sum);
		return width * height > min;
	}
}
