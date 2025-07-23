package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.ModFoods;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.crafting.CrackerRecipe;
import io.github.coffeecatrailway.food.common.item.crafting.PizzaRecipe;
import io.github.coffeecatrailway.food.common.item.crafting.SandwichRecipe;
import io.github.coffeecatrailway.food.datagen.recipes.ChoppingBoardRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class RecipeGenerator extends RecipeProvider
{
	public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
	{
		super(output, lookupProvider);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput)
	{
		// Crafting Materials
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEAR_WOODEN).pattern(" s ").pattern("sms").pattern(" s ").define('m', net.minecraft.tags.ItemTags.PLANKS).define('s', Tags.Items.RODS_WOODEN)
				.showNotification(false).group("gear").unlockedBy("criteria", has(net.minecraft.tags.ItemTags.PLANKS)).save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEAR_STONE).pattern(" s ").pattern("sms").pattern(" s ").define('m', Tags.Items.STONES).define('s', Tags.Items.RODS_WOODEN)
				.showNotification(false).group("gear").unlockedBy("criteria", has(Tags.Items.STONES)).save(recipeOutput);

		// Tools
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CURDLER_WOODEN).pattern("s").pattern("s").pattern("g").define('g', ModItemTags.GEARS_WOODEN).define('s', Tags.Items.RODS_WOODEN)
				.showNotification(false).group("curdler").unlockedBy("criteria", has(ModItemTags.GEARS_WOODEN)).save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CURDLER_STONE).pattern("s").pattern("s").pattern("g").define('g', ModItemTags.GEARS_STONE).define('s', Tags.Items.RODS_WOODEN)
				.showNotification(false).group("curdler").unlockedBy("criteria", has(ModItemTags.GEARS_STONE)).save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ROLLING_PIN_WOODEN).pattern("sms").define('m', net.minecraft.tags.ItemTags.PLANKS).define('s', Tags.Items.RODS_WOODEN)
				.showNotification(false).group("rolling_pin").unlockedBy("criteria", has(net.minecraft.tags.ItemTags.PLANKS)).save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ROLLING_PIN_STONE).pattern("sms").define('m', Tags.Items.STONES).define('s', Tags.Items.RODS_WOODEN)
				.showNotification(false).group("rolling_pin").unlockedBy("criteria", has(Tags.Items.STONES)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.GRIND_STONES).requires(Items.COBBLESTONE).unlockedBy("has_stone", has(Items.COBBLESTONE)).save(recipeOutput);

		this.knife(ModItems.KNIFE_WOODEN, net.minecraft.tags.ItemTags.PLANKS, recipeOutput);
		this.knife(ModItems.KNIFE_STONE, Tags.Items.STONES, recipeOutput);
		this.knife(ModItems.KNIFE_COPPER, Items.COPPER_INGOT, recipeOutput);
		this.knife(ModItems.KNIFE_GOLD, Tags.Items.INGOTS_GOLD, recipeOutput);
		this.knife(ModItems.KNIFE_IRON, Tags.Items.INGOTS_IRON, recipeOutput);
		this.knife(ModItems.KNIFE_DIAMOND, Tags.Items.GEMS_DIAMOND, recipeOutput);
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.KNIFE_DIAMOND), Ingredient.of(Tags.Items.INGOTS_NETHERITE), RecipeCategory.TOOLS, ModItems.KNIFE_NETHERITE.get())
				.unlocks("has_netherite", has(Tags.Items.INGOTS_NETHERITE)).save(recipeOutput, FoodMod.id("knife_netherite"));

		// Foods
		// TODO: Cheese slice
//			ShapelessRecipeBuilder.shapeless(ModBlocks.BLOCK_OF_CHEESE.get(), 2).group("block_of_cheese").requires(Items.MILK_BUCKET).requires(ModItemTags.CURDLERS).unlockedBy("has_milk", has(Items.MILK_BUCKET))
//					.unlockedBy("has_curdler", has(ModItemTags.CURDLERS)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESE_SLICE.get(), 4).group("cheese_slice").requires(ModBlocks.BLOCK_OF_CHEESE.get()).requires(ModItemTags.TOOLS_KNIFE).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_CHEESE.get()))
//					.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.CHEESE_SLICE.get(), 5, Ingredient.of(ModBlocks.BLOCK_OF_CHEESE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_CHEESE.get())).save(recipeOutput, FoodMod.id("cheese_slice_chopping_board"));
//
//			ShapelessRecipeBuilder.shapeless(ModItems.BLUE_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(ModBlocks.BLOCK_OF_BLUE_CHEESE.get()).requires(ModItemTags.TOOLS_KNIFE).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_BLUE_CHEESE.get()))
//					.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.BLUE_CHEESE_SLICE.get(), 5, Ingredient.of(ModBlocks.BLOCK_OF_BLUE_CHEESE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_BLUE_CHEESE.get())).save(recipeOutput, FoodMod.id("blue_cheese_slice_chopping_board"));
//
//			ShapedRecipeBuilder.shaped(ModBlocks.BLOCK_OF_GOUDA_CHEESE.get()).group("block_of_cheese").define('r', ModItemTags.RED_DYES_COMMON).define('w', Items.HONEYCOMB).define('c', Ingredient.of(ModBlocks.BLOCK_OF_CHEESE.get(), ModBlocks.BLOCK_OF_GOAT_CHEESE.get())).pattern("wrw").pattern("rcr").pattern("wrw")
//					.unlockedBy("has_dye", has(ModItemTags.RED_DYES_COMMON)).unlockedBy("has_wax", has(Items.HONEYCOMB)).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_CHEESE.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.GOUDA_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(ModBlocks.BLOCK_OF_GOUDA_CHEESE.get()).requires(ModItemTags.TOOLS_KNIFE).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_GOUDA_CHEESE.get()))
//					.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.GOUDA_CHEESE_SLICE.get(), 5, Ingredient.of(ModBlocks.BLOCK_OF_GOUDA_CHEESE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_GOUDA_CHEESE.get())).save(recipeOutput, FoodMod.id("gouda_cheese_slice_chopping_board"));
//
//			ShapelessRecipeBuilder.shapeless(ModItems.SWISS_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(ModBlocks.BLOCK_OF_SWISS_CHEESE.get()).requires(ModItemTags.TOOLS_KNIFE).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_SWISS_CHEESE.get()))
//					.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.SWISS_CHEESE_SLICE.get(), 5, Ingredient.of(ModBlocks.BLOCK_OF_SWISS_CHEESE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_SWISS_CHEESE.get())).save(recipeOutput, FoodMod.id("swiss_cheese_slice_chopping_board"));
//
//			ShapelessRecipeBuilder.shapeless(ModBlocks.BLOCK_OF_GOAT_CHEESE.get(), 2).group("block_of_cheese").requires(ModFluids.GOAT_MILK_BUCKET.get()).requires(ModItemTags.CURDLERS).unlockedBy("has_milk", has(ModFluids.GOAT_MILK_BUCKET.get()))
//					.unlockedBy("has_curdler", has(ModItemTags.CURDLERS)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.GOAT_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(ModBlocks.BLOCK_OF_GOAT_CHEESE.get()).requires(ModItemTags.TOOLS_KNIFE).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_GOAT_CHEESE.get()))
//					.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.GOAT_CHEESE_SLICE.get(), 5, Ingredient.of(ModBlocks.BLOCK_OF_GOAT_CHEESE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlockedBy("has_cheese", has(ModBlocks.BLOCK_OF_GOAT_CHEESE.get())).save(recipeOutput, FoodMod.id("goat_cheese_slice_chopping_board"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALT.get(), 2).requires(ModItems.GRIND_STONES.get()).requires(Tags.Items.STONES).unlockedBy("has_stone", has(Tags.Items.STONES)).unlockedBy("has_grind_stones", has(ModItems.GRIND_STONES.get())).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FLOUR.get(), 3).requires(ModItems.GRIND_STONES.get()).requires(Tags.Items.CROPS_WHEAT).unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT)).unlockedBy("has_grind_stones", has(ModItems.GRIND_STONES.get())).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DOUGH.get(), 3).requires(Tags.Items.CROPS_WHEAT).requires(Items.SUGAR).requires(ModItemTags.FOODS_SALT).requires(ModItemTags.FOODS_FLOUR).unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT)).unlockedBy("has_sugar", has(Items.SUGAR)).unlockedBy("has_salt", has(ModItemTags.FOODS_SALT)).unlockedBy("has_flour", has(ModItemTags.FOODS_FLOUR)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.UNFIRED_PIZZA_BASE.get()).requires(ModItemTags.TOOLS_ROLLING_PIN).requires(ModItems.DOUGH.get()).unlockedBy("has_dough", has(ModItems.DOUGH.get())).unlockedBy("has_rolling_pin", has(ModItemTags.TOOLS_ROLLING_PIN)).save(recipeOutput);
		ChoppingBoardRecipeBuilder.recipe(ModItems.UNFIRED_PIZZA_BASE.get(), Ingredient.of(ModItems.DOUGH.get())).tool(Ingredient.of(ModItemTags.TOOLS_ROLLING_PIN)).unlocks("has_dough", has(ModItems.DOUGH.get())).save(recipeOutput, FoodMod.id("unfired_pizza_base_chopping_board"));
		SpecialRecipeBuilder.special(PizzaRecipe::new).save(recipeOutput, FoodMod.id("pizza"));

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.UNBAKED_BREAD.get(), 2).define('d', ModItemTags.FOODS_DOUGH).pattern("ddd").unlockedBy("has_dough", has(ModItemTags.FOODS_DOUGH)).save(recipeOutput);
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.UNBAKED_BREAD.get()), RecipeCategory.FOOD, Items.BREAD, .2f, 200).unlockedBy("has_unbaked_bread", has(ModItems.UNBAKED_BREAD.get())).save(recipeOutput, FoodMod.id("bread_smoking"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BREAD_SLICE.get(), ModFoods.SLICE_PER_BREAD).requires(ModItemTags.TOOLS_KNIFE).requires(Items.BREAD).unlockedBy("has_bread", has(Items.BREAD)).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
		this.cookedFood(ModItems.BREAD_SLICE, ModItems.TOAST, .35f, 200, recipeOutput);
		ChoppingBoardRecipeBuilder.recipe(ModItems.BREAD_SLICE.get(), ModFoods.SLICE_PER_BREAD, Ingredient.of(Items.BREAD)).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_bread", has(Items.BREAD)).save(recipeOutput, FoodMod.id("bread_slice_chopping_board"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MOLDY_BREAD_SLICE.get()).requires(Blocks.MOSS_BLOCK).requires(ModItems.BREAD_SLICE.get()).unlockedBy("has_moss", has(Blocks.MOSS_BLOCK)).unlockedBy("has_bread_slice", has(ModItems.BREAD_SLICE.get())).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.UNBAKED_CRACKER.get(), 3).requires(ModItems.UNFIRED_PIZZA_BASE).requires(ModItemTags.TOOLS_KNIFE).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
		this.cookedFood(ModItems.UNBAKED_CRACKER.get(), ModItems.CRACKER.get(), .35f, 100, recipeOutput);
		SpecialRecipeBuilder.special(CrackerRecipe::new).save(recipeOutput, FoodMod.id("cracker_special"));
		ChoppingBoardRecipeBuilder.recipe(ModItems.UNBAKED_CRACKER.get(), 3, Ingredient.of(ModItems.UNFIRED_PIZZA_BASE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_unfired_pizza_base", has(ModItems.UNFIRED_PIZZA_BASE.get())).save(recipeOutput, FoodMod.id("unbaked_cracker_chopping_board"));

		this.cookedFood(ModItems.CRACKED_EGG.get(), ModItems.FRIED_EGG.get(), .15f, 100, recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_CRACKED_EGG.get()).requires(Tags.Items.DYES_GREEN).requires(ModItems.CRACKED_EGG.get()).unlockedBy("has_egg", has(ModItems.CRACKED_EGG.get())).unlockedBy("has_dye", has(Tags.Items.DYES_GREEN)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HAM_SLICE.get(), 3).requires(ModItemTags.TOOLS_KNIFE).requires(Items.PORKCHOP).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE))
				.unlockedBy("has_porkchop", has(Items.PORKCHOP)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COOKED_HAM_SLICE.get(), 3).requires(ModItemTags.TOOLS_KNIFE).requires(Items.COOKED_PORKCHOP).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE))
				.unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP)).save(recipeOutput);
		ChoppingBoardRecipeBuilder.recipe(ModItems.HAM_SLICE.get(), 3, Ingredient.of(Items.PORKCHOP)).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_porkchop", has(Items.PORKCHOP)).save(recipeOutput, FoodMod.id("ham_slice_chopping_board"));
		ChoppingBoardRecipeBuilder.recipe(ModItems.COOKED_HAM_SLICE.get(), 3, Ingredient.of(Items.COOKED_PORKCHOP)).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_porkchop", has(Items.COOKED_PORKCHOP)).save(recipeOutput, FoodMod.id("cooked_ham_slice_chopping_board"));
		this.cookedFood(ModItems.HAM_SLICE.get(), ModItems.COOKED_HAM_SLICE.get(), .35f, 200, recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_HAM_SLICE.get()).requires(Tags.Items.DYES_GREEN).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_ham", has(ModItems.HAM_SLICE.get())).unlockedBy("has_dye", has(Tags.Items.DYES_GREEN)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RAW_BACON.get(), 2).requires(ModItemTags.TOOLS_KNIFE).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_ham", has(ModItems.HAM_SLICE.get())).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COOKED_BACON.get(), 2).requires(ModItemTags.TOOLS_KNIFE).requires(ModItems.COOKED_HAM_SLICE.get()).unlockedBy("has_ham", has(ModItems.COOKED_HAM_SLICE.get())).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput, FoodMod.id("cooked_bacon_knife"));
		ChoppingBoardRecipeBuilder.recipe(ModItems.RAW_BACON.get(), 2, Ingredient.of(ModItems.HAM_SLICE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_ham", has(ModItems.HAM_SLICE.get())).save(recipeOutput, FoodMod.id("bacon_chopping_board"));
		ChoppingBoardRecipeBuilder.recipe(ModItems.COOKED_BACON.get(), 2, Ingredient.of(ModItems.COOKED_HAM_SLICE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_ham", has(ModItems.COOKED_HAM_SLICE.get())).save(recipeOutput, FoodMod.id("cooked_bacon_chopping_board"));
		this.cookedFood(ModItems.RAW_BACON.get(), ModItems.COOKED_BACON.get(), .2f, 100, recipeOutput);

		SpecialRecipeBuilder.special(SandwichRecipe::new).save(recipeOutput, FoodMod.id("sandwich"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_CROWN.get()).requires(ModItems.PINEAPPLE.get()).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE.get())).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_RING.get(), 4).requires(ModItemTags.TOOLS_KNIFE).requires(ModItems.PINEAPPLE.get()).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE.get()))
				.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_BITES.get(), 3).requires(ModItemTags.TOOLS_KNIFE).requires(ModItems.PINEAPPLE_RING.get()).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE_RING.get()))
				.unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE)).save(recipeOutput);
		ChoppingBoardRecipeBuilder.recipe(ModItems.PINEAPPLE_RING.get(), 4, Ingredient.of(ModItems.PINEAPPLE.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_pineapple", has(ModItems.PINEAPPLE.get())).save(recipeOutput, FoodMod.id("pineapple_ring_chopping_board"));
		ChoppingBoardRecipeBuilder.recipe(ModItems.PINEAPPLE_BITES.get(), 3, Ingredient.of(ModItems.PINEAPPLE_RING.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_pineapple", has(ModItems.PINEAPPLE_RING.get())).save(recipeOutput, FoodMod.id("pineapple_bites_chopping_board"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_SAUCE.get()).requires(Items.BOWL).requires(Items.PUMPKIN_SEEDS).requires(ModItemTags.FOODS_TOMATO).unlockedBy("has_bowl", has(Items.BOWL))
				.unlockedBy("has_pumpkin_seeds", has(Items.PUMPKIN_SEEDS)).unlockedBy("has_tomato", has(ModItemTags.FOODS_TOMATO)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_SLICE.get(), 4).requires(ModItemTags.TOOLS_KNIFE).requires(ModItems.TOMATO.get()).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE))
				.unlockedBy("has_tomato", has(ModItems.TOMATO.get())).save(recipeOutput);
		ChoppingBoardRecipeBuilder.recipe(ModItems.TOMATO_SLICE.get(), 4, Ingredient.of(ModItems.TOMATO.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_tomato", has(ModItems.TOMATO.get())).save(recipeOutput, FoodMod.id("tomato_slice_chopping_board"));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_SEEDS.get(), 4).requires(ModItems.TOMATO.get()).unlockedBy("has_tomato", has(ModItems.TOMATO.get())).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CORN_KERNELS.get(), 8).requires(ModItemTags.TOOLS_KNIFE).requires(ModItems.CORN_COB.get()).unlockedBy("has_knife", has(ModItemTags.TOOLS_KNIFE))
				.unlockedBy("has_corn_cob", has(ModItems.CORN_COB.get())).save(recipeOutput);
		ChoppingBoardRecipeBuilder.recipe(ModItems.CORN_KERNELS.get(), 8, Ingredient.of(ModItems.CORN_COB.get())).tool(Ingredient.of(ModItemTags.TOOLS_KNIFE)).unlocks("has_corn_cob", has(ModItems.CORN_COB.get())).save(recipeOutput, FoodMod.id("corn_kernels_chopping_board"));
		this.cookedFood(ModItems.CORN_KERNELS.get(), ModItems.DRIED_CORN_KERNELS.get(), .2f, 100, recipeOutput);

		this.cookedFood(ModItems.MOUSE.get(), ModItems.COOKED_MOUSE.get(), .2f, 100, recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.POPCORN_BAG.get()).define('p', Items.PAPER).define('r', Tags.Items.DYES_RED)
				.pattern("p p").pattern("rpr").unlockedBy("has_paper", has(Items.PAPER)).unlockedBy("has_red_dye", has(Tags.Items.DYES_RED)).save(recipeOutput);

//			PopcornRecipeBuilder.popcorn(ModItems.POPCORN.get(), 2, 50, Ingredient.of(ModItems.ROCK_SALT.get()))
//					.unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);
//			PopcornRecipeBuilder.popcorn(ModItems.CHEESY_POPCORN.get(), 75, Ingredient.of(ModItems.ROCK_SALT.get())).flavouring(Ingredient.of(ModItemTags.CHEESE_SLICE_COMMON))
//					.unlockedBy("has_cheese_slice", has(ModItemTags.CHEESE_SLICE_COMMON)).unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);
//			PopcornRecipeBuilder.popcorn(ModItems.CARAMEL_POPCORN.get(), 75, Ingredient.of(ModItems.ROCK_SALT.get())).flavouring(Ingredient.of(ModItemTags.SUGAR_COMMON))
//					.unlockedBy("has_sugar", has(ModItemTags.SUGAR_COMMON)).unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);
//			PopcornRecipeBuilder.popcorn(ModItems.MAPLE_POPCORN.get(), 75, Ingredient.of(ModItems.ROCK_SALT.get())).flavouring(Ingredient.of(ModItems.MAPLE_SYRUP.get()))
//					.unlockedBy("has_syrup", has(ModItems.MAPLE_SYRUP.get())).unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);

//			SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.UNBAKED_CROISSANT.get()), ModItems.CROISSANT.get(), .2f, 100).unlockedBy("has_unbaked_croissant", has(ModItems.UNBAKED_CROISSANT.get())).save(recipeOutput);
//			SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItems.UNBAKED_CROISSANT.get()), ModItems.CROISSANT.get(), .2f, 50, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_unbaked_croissant", has(ModItems.UNBAKED_CROISSANT.get())).save(recipeOutput, FoodMod.id("croissant_smoking"));
//			SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItems.UNBAKED_CROISSANT.get()), ModItems.CROISSANT.get(), .2f, 300, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_unbaked_croissant", has(ModItems.UNBAKED_CROISSANT.get())).save(recipeOutput, FoodMod.id("croissant_campfire"));
//
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESY_CROISSANT.get()).group("croissant").requires(ModItems.CROISSANT.get()).requires(ModItemTags.CHEESE_SLICE_COMMON).unlockedBy("has_croissant", has(ModItems.CROISSANT.get())).unlockedBy("has_cheese_slice", has(ModItemTags.CHEESE_SLICE_COMMON)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESY_HAM_CROISSANT.get()).group("croissant").requires(ModItems.CHEESY_CROISSANT.get()).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_croissant", has(ModItems.CHEESY_CROISSANT.get())).unlockedBy("has_ham_slice", has(ModItems.HAM_SLICE.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESY_HAM_CROISSANT.get()).group("croissant").requires(ModItems.CROISSANT.get()).requires(ModItemTags.CHEESE_SLICE_COMMON).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_croissant", has(ModItems.CROISSANT.get())).unlockedBy("has_cheese_slice", has(ModItemTags.CHEESE_SLICE_COMMON)).unlockedBy("has_ham_slice", has(ModItems.HAM_SLICE.get())).save(recipeOutput, FoodMod.id("cheesy_croissant_with_ham_alt"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MAPLE_SYRUP.get()).requires(ModItems.MAPLE_SAP_BOTTLE).requires(Items.SUGAR).unlockedBy("has_maple_sap", has(ModItems.MAPLE_SAP_BOTTLE)).save(recipeOutput);

		// Other
//			ShapedRecipeBuilder.shaped(ModBlocks.PIZZA_OVEN.get()).define('b', Items.BRICK).define('t', Blocks.WHITE_TERRACOTTA).define('c', Ingredient.of(ModItemTags.CAMPFIRES))
//					.pattern(" t ").pattern("tct").pattern("bbb").unlockedBy("has_bricks", has(Items.BRICK)).unlockedBy("has_terracotta", has(Blocks.WHITE_TERRACOTTA))
//					.unlockedBy("has_campfire", has(ModItemTags.CAMPFIRES)).save(recipeOutput);
//
//			ShapedRecipeBuilder.shaped(ModBlocks.GRILL.get()).define('i', ModItemTags.IRON_INGOTS_COMMON).define('r', ModItemTags.REDSTONE_COMMON).define('s', Blocks.SMOKER).define('b', Blocks.IRON_BARS)
//					.pattern("i i").pattern("rsi").pattern("b b").unlockedBy("has_iron", has(ModItemTags.IRON_INGOTS_COMMON)).unlockedBy("has_redstone", has(ModItemTags.REDSTONE_COMMON))
//					.unlockedBy("has_smoker", has(Blocks.SMOKER)).unlockedBy("has_bars", has(Blocks.IRON_BARS)).save(recipeOutput);
//
//			ShapedRecipeBuilder.shaped(ModBlocks.POPCORN_MACHINE.get()).define('i', ModItemTags.IRON_INGOTS_COMMON).define('r', Blocks.RED_CONCRETE).define('w', Blocks.WHITE_CONCRETE).define('b', Items.BUCKET).define('g', Blocks.GLASS_PANE)
//					.pattern("rwr").pattern("gbg").pattern("i i").unlockedBy("has_iron", has(ModItemTags.IRON_INGOTS_COMMON)).unlockedBy("has_red_concrete", has(Blocks.RED_CONCRETE))
//					.unlockedBy("has_white_concrete", has(Blocks.WHITE_CONCRETE)).unlockedBy("has_bucket", has(Items.BUCKET)).unlockedBy("has_glass_pane", has(Blocks.GLASS_PANE)).save(recipeOutput);

//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_WOOD.get(), 3).define('l', ModBlocks.MAPLE_LOG.get()).pattern("ll").pattern("ll").group("bark").unlockedBy("has_log", has(ModBlocks.MAPLE_LOG.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.STRIPPED_MAPLE_WOOD.get(), 3).define('l', ModBlocks.STRIPPED_MAPLE_LOG.get()).pattern("ll").pattern("ll").group("bark").unlockedBy("has_log", has(ModBlocks.STRIPPED_MAPLE_LOG.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModBlocks.MAPLE_PLANKS.get(), 4).requires(Ingredient.of(ModItemTags.MAPLE_LOGS)).group("planks").unlockedBy("has_log", has(ModItemTags.MAPLE_LOGS)).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_STAIRS.get(), 4).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("p  ").pattern("pp ").pattern("ppp").group("wooden_stairs").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_SLAB.get(), 6).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("ppp").group("wooden_slab").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_SIGN.getFirst().get(), 3).group("sign").define('p', ModBlocks.MAPLE_PLANKS.get()).define('s', Items.STICK).pattern("ppp").pattern("ppp").pattern(" s ").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_PRESSURE_PLATE.get()).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("pp").group("wooden_pressure_plate").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModBlocks.MAPLE_BUTTON.get()).requires(ModBlocks.MAPLE_PLANKS.get()).group("wooden_button").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_FENCE.get(), 3).define('s', Items.STICK).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("psp").pattern("psp").group("wooden_fence").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_FENCE_GATE.get()).define('s', Items.STICK).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("sps").pattern("sps").group("wooden_fence_gate").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_TRAPDOOR.get(), 2).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("ppp").pattern("ppp").group("wooden_trapdoor").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModBlocks.MAPLE_DOOR.get(), 3).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("pp").pattern("pp").pattern("pp").group("wooden_door").unlockedBy("has_planks", has(ModBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModItems.MAPLE_BOAT.get()).define('p', ModBlocks.MAPLE_PLANKS.get()).pattern("p p").pattern("ppp").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(recipeOutput);
//
//			ShapedRecipeBuilder.shaped(ModBlocks.TREE_TAP.get()).define('i', ModItemTags.IRON_INGOTS_COMMON).define('n', ModItemTags.IRON_NUGGETS_COMMON).pattern(" n").pattern("ii").pattern(" n").unlockedBy("has_iron", has(ModItemTags.IRON_INGOTS_COMMON)).unlockedBy("has_iron_nugget", has(ModItemTags.IRON_NUGGETS_COMMON)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.OAK_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.OAK_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.OAK_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BIRCH_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.BIRCH_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.BIRCH_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.SPRUCE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.SPRUCE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.SPRUCE_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.JUNGLE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.JUNGLE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.JUNGLE_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ACACIA_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.ACACIA_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.ACACIA_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.DARK_OAK_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.DARK_OAK_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.DARK_OAK_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CRIMSON_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.CRIMSON_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.CRIMSON_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WARPED_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.WARPED_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.WARPED_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CHERRY_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.CHERRY_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.CHERRY_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.MANGROVE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.MANGROVE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.MANGROVE_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BAMBOO_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.BAMBOO_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.BAMBOO_PRESSURE_PLATE)).save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.STONE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.STONE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.STONE_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.POLISHED_BLACKSTONE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.GOLD_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE)).save(recipeOutput);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.IRON_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)).save(recipeOutput);

//			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.MAPLE_CHOPPING_BOARD.get()).group("chopping_board").requires(ModBlocks.MAPLE_PRESSURE_PLATE.get()).unlockedBy("has_pressure_plate", has(ModBlocks.MAPLE_PRESSURE_PLATE.get())).save(recipeOutput);
//
//			ChoppingBoardRecipeBuilder.recipe(ModItems.UNBAKED_CROISSANT.get(), Ingredient.of(ModItems.UNBAKED_PIZZA_BASE.get())).tool(Ingredient.of(ModItemTags.ROLLING_PINS)).unlockedBy("has_pizza_base", has(ModItems.UNBAKED_PIZZA_BASE.get())).save(recipeOutput);
	}

	private void cookedFood(ItemLike raw, ItemLike cooked, float experience, int baseCookTime, RecipeOutput recipeOutput)
	{
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(raw), RecipeCategory.FOOD, cooked, experience, baseCookTime * 3).unlockedBy("has_raw", has(raw)).save(recipeOutput, FoodMod.id(getItemName(cooked) + "_campfire"));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(raw), RecipeCategory.FOOD, cooked, experience, baseCookTime).unlockedBy("has_raw", has(raw)).save(recipeOutput, FoodMod.id(getItemName(cooked) + "_smelting"));
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(raw), RecipeCategory.FOOD, cooked, experience, baseCookTime / 2).unlockedBy("has_raw", has(raw)).save(recipeOutput, FoodMod.id(getItemName(cooked) + "_smoking"));
	}

	private void knife(ItemLike result, ItemLike material, RecipeOutput recipeOutput)
	{
		this.knifeBuilder(result).define('m', material).unlockedBy("criteria", has(material)).save(recipeOutput);
	}

	private void knife(ItemLike result, TagKey<Item> material, RecipeOutput recipeOutput)
	{
		this.knifeBuilder(result).define('m', material).unlockedBy("criteria", has(material)).save(recipeOutput);
	}

	private ShapedRecipeBuilder knifeBuilder(ItemLike result)
	{
		return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern("m").pattern("s").define('s', Tags.Items.RODS_WOODEN).showNotification(true).group("knife");
	}
}
