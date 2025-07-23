package io.github.coffeecatrailway.food.common.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 22/07/2025
 */
public class ChoppingBoardRecipe implements Recipe<ChoppingBoardRecipeInput>
{
	private final Ingredient ingredient;
	private final Ingredient tool;
	private final ItemStack result;

	public ChoppingBoardRecipe(Ingredient ingredient, Ingredient tool, ItemStack result) {
		this.ingredient = ingredient;
		this.tool = tool;
		this.result = result;
	}

	@Override
	public boolean matches(ChoppingBoardRecipeInput input, Level level)
	{
		return this.ingredient.test(input.ingredient()) && this.tool.test(input.tool());
	}

	@Override
	public ItemStack assemble(ChoppingBoardRecipeInput input, HolderLookup.Provider registries)
	{
		return result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height)
	{
		return true;
	}

//	public Ingredient getIngredient()
//	{
//		return this.ingredient;
//	}
//
//	public Ingredient getTool()
//	{
//		return this.tool;
//	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries)
	{
		return this.result;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return ModRecipes.CHOPPING_BOARD_SERIALIZER.get();
	}

	@Override
	public boolean isSpecial()
	{
		return true;
	}

	@Override
	public RecipeType<?> getType()
	{
		return ModRecipes.CHOPPING_BOARD_TYPE.get();
	}

	public static class Serializer implements RecipeSerializer<ChoppingBoardRecipe>
	{
		private static final MapCodec<ChoppingBoardRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
				Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
				Ingredient.CODEC.fieldOf("tool").forGetter(recipe -> recipe.tool),
				ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
		).apply(instance, ChoppingBoardRecipe::new));
		private static final StreamCodec<RegistryFriendlyByteBuf, ChoppingBoardRecipe> STREAM_CODEC = StreamCodec.of(
				Serializer::toNetwork, Serializer::fromNetwork
		);

		@Override
		public MapCodec<ChoppingBoardRecipe> codec()
		{
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ChoppingBoardRecipe> streamCodec()
		{
			return STREAM_CODEC;
		}

		private static ChoppingBoardRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
		{
			Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient tool = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
			return new ChoppingBoardRecipe(ingredient, tool, result);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, ChoppingBoardRecipe recipe)
		{
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.tool);
			ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
		}
	}
}
