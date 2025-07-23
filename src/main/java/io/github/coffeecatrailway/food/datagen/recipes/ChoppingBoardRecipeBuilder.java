package io.github.coffeecatrailway.food.datagen.recipes;

import io.github.coffeecatrailway.food.common.item.crafting.ChoppingBoardRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 23/07/2025
 */
public class ChoppingBoardRecipeBuilder
{
	private final Ingredient ingredient;
	private Ingredient tool = Ingredient.EMPTY;
	private final Item result;
	private final int count;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	private ChoppingBoardRecipeBuilder(Ingredient ingredient, ItemLike result, int count)
	{
		this.ingredient = ingredient;
		this.result = result.asItem();
		this.count = count;
	}

	public static ChoppingBoardRecipeBuilder recipe(ItemLike result, Ingredient ingredient)
	{
		return recipe(result, 1, ingredient);
	}

	public static ChoppingBoardRecipeBuilder recipe(ItemLike result, int count, Ingredient ingredient)
	{
		return new ChoppingBoardRecipeBuilder(ingredient, result, count);
	}

	public ChoppingBoardRecipeBuilder tool(Ingredient tool)
	{
		this.tool = tool;
		return this;
	}

	public ChoppingBoardRecipeBuilder unlocks(String key, Criterion<?> criterion)
	{
		this.criteria.put(key, criterion);
		return this;
	}

	public void save(RecipeOutput recipeOutput, String recipeId)
	{
		this.save(recipeOutput, ResourceLocation.parse(recipeId));
	}

	public void save(RecipeOutput recipeOutput, ResourceLocation recipeId)
	{
		this.ensureValid(recipeId);
		Advancement.Builder builder = recipeOutput.advancement()
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
				.rewards(AdvancementRewards.Builder.recipe(recipeId))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(builder::addCriterion);
		ChoppingBoardRecipe recipe = new ChoppingBoardRecipe(this.ingredient, this.tool, new ItemStack(this.result, this.count));
		recipeOutput.accept(recipeId, recipe, builder.build(recipeId.withPrefix("recipes/")));
	}

	private void ensureValid(ResourceLocation location)
	{
		if (this.criteria.isEmpty())
			throw new IllegalStateException("No way of obtaining recipe " + location);
	}
}
