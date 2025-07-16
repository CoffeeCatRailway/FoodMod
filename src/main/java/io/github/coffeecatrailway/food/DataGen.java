package io.github.coffeecatrailway.food;

import io.github.coffeecatrailway.food.common.ModFoods;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.crafting.CrackerRecipe;
import io.github.coffeecatrailway.food.common.item.crafting.PizzaRecipe;
import io.github.coffeecatrailway.food.common.item.crafting.SandwichRecipe;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 21/05/2025
 */
public class DataGen
{
	public static class ItemModel extends ItemModelProvider
	{
		public ItemModel(PackOutput output, ExistingFileHelper existingFileHelper)
		{
			super(output, FoodMod.MODID, existingFileHelper);
		}

		@Override
		protected void registerModels()
		{
			// Crafting Materials
			this.basicItem(ModItems.GEAR_WOODEN.get());
			this.basicItem(ModItems.GEAR_STONE.get());

			// Tools
			this.basicItem(ModItems.CURDLER_WOODEN.get());
			this.basicItem(ModItems.CURDLER_STONE.get());

			this.basicItem(ModItems.ROLLING_PIN_WOODEN.get());
			this.basicItem(ModItems.ROLLING_PIN_STONE.get());

			this.basicItem(ModItems.GRIND_STONES.get());

			this.basicItem(ModItems.KNIFE_WOODEN.get());
			this.basicItem(ModItems.KNIFE_STONE.get());
			this.basicItem(ModItems.KNIFE_COPPER.get());
			this.basicItem(ModItems.KNIFE_GOLD.get());
			this.basicItem(ModItems.KNIFE_IRON.get());
			this.basicItem(ModItems.KNIFE_DIAMOND.get());
			this.basicItem(ModItems.KNIFE_NETHERITE.get());

			// Foods
			this.basicItem(ModItems.CHEESE_SLICE.get());
			this.basicItem(ModItems.BLUE_CHEESE_SLICE.get());
			this.basicItem(ModItems.GOUDA_CHEESE_SLICE.get());
			this.basicItem(ModItems.SWISS_CHEESE_SLICE.get());
			this.basicItem(ModItems.SWISS_CHEESE_BITES.get());
			this.basicItem(ModItems.GOAT_CHEESE_SLICE.get());

			this.basicItem(ModItems.SALT.get());
			this.basicItem(ModItems.FLOUR.get());

			this.basicItem(ModItems.DOUGH.get());

			this.basicItem(ModItems.UNFIRED_PIZZA_BASE.get());
			this.basicItem(ModItems.FIRED_PIZZA_BASE.get());

			this.basicItem(ModItems.UNBAKED_BREAD.get());
			this.basicItem(ModItems.BREAD_SLICE.get());
			this.basicItem(ModItems.TOAST.get());
			this.basicItem(ModItems.MOLDY_BREAD_SLICE.get());

			this.basicItem(ModItems.UNBAKED_CRACKER.get());
			this.basicItem(ModItems.CRACKER_DUMMY.get(), FoodMod.id("item/cracker"));

			this.basicItem(ModItems.CRACKED_EGG.get());
			this.basicItem(ModItems.FRIED_EGG.get());
			this.basicItem(ModItems.GREEN_CRACKED_EGG.get());

			this.basicItem(ModItems.HAM_SLICE.get());
			this.basicItem(ModItems.COOKED_HAM_SLICE.get());
			this.basicItem(ModItems.GREEN_HAM_SLICE.get());

			this.basicItem(ModItems.RAW_BACON.get());
			this.basicItem(ModItems.COOKED_BACON.get());

			this.basicItem(ModItems.PINEAPPLE.get(), FoodMod.id("block/pineapple_stage_4"));
			this.basicItem(ModItems.PINEAPPLE_RING.get());
			this.basicItem(ModItems.PINEAPPLE_BITES.get());

			this.basicItem(ModItems.TOMATO.get());
			this.basicItem(ModItems.TOMATO_SLICE.get());
			this.basicItem(ModItems.TOMATO_SAUCE.get());

			this.basicItem(ModItems.CORN_KERNELS.get());
			this.basicItem(ModItems.DRIED_CORN_KERNELS.get());

			this.basicItem(ModItems.POPCORN_BAG.get());
			this.basicItem(ModItems.POPCORN.get());
			this.basicItem(ModItems.CHEESY_POPCORN.get());
			this.basicItem(ModItems.CARAMEL_POPCORN.get());
			this.basicItem(ModItems.MAPLE_POPCORN.get());

			this.basicItem(ModItems.MOUSE.get());
			this.basicItem(ModItems.COOKED_MOUSE.get());

			this.basicItem(ModItems.FOOD_SCRAPS.get());

			this.basicItem(ModItems.MAPLE_SAP_BOTTLE.get());
			this.basicItem(ModItems.MAPLE_SYRUP.get());
		}

		public ItemModelBuilder basicItem(Item item, ResourceLocation texture)
		{
			return getBuilder(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).toString())
					.parent(new ModelFile.UncheckedModelFile("item/generated"))
					.texture("layer0", texture);
		}
	}

	public static class Language extends LanguageProvider
	{
		public static final Map<Supplier<? extends Item>, String> ITEMS = new HashMap<>();

		public Language(PackOutput output)
		{
			super(output, FoodMod.MODID, "en_us");
		}

		@Override
		protected void addTranslations()
		{
			this.add("item." + FoodMod.MODID + ".green_food.drseuss", "Yay to Dr. Seuss for his green eggs and ham!");
			this.add("item." + FoodMod.MODID + ".green_food.birthday_line1", "And a happy birthday to the great Dr. Seuss!");
			this.add("item." + FoodMod.MODID + ".green_food.birthday_line2", "Born 2 March 1904, Died 24 September 1991");

			this.add("item." + FoodMod.MODID + ".food_combo.info", "to show ingredients");
			this.add("item." + FoodMod.MODID + ".food_combo.info.nutrition", "Nutrition: %s");
			this.add("item." + FoodMod.MODID + ".food_combo.info.saturation", "Saturation: %s");
			this.add("item." + FoodMod.MODID + ".sandwich.toasted", "Toasted");
			this.add("item." + FoodMod.MODID + ".pizza.unfired", "Unfired");
			this.add("item." + FoodMod.MODID + ".pizza.fired", "Fired");

			// Other
			this.add("tooltip." + FoodMod.MODID + ".hold_shift", "[HOLD SHIFT %s]");

			// Config
			this.add(FoodMod.MODID + ".configuration.sandwich", "Sandwich");
			this.addConfig(FoodConfigs.CLIENT.rotateIngredients, "Rotate Ingredients");
			this.addConfig(FoodConfigs.CLIENT.showNutritionSaturation, "Show Nutrition/Saturation");

			this.addConfig(FoodConfigs.SERVER.allowPotions, "Allow Potions");
			this.addConfig(FoodConfigs.SERVER.toastedModifier, "Toasted Modifier");
			this.addConfig(FoodConfigs.SERVER.averageIngredients, "Average Ingredients");
			this.add(FoodMod.MODID + ".configuration.combinedModifiers", "Combined Modifiers");
			this.addConfig(FoodConfigs.SERVER.nutritionModifier, "Nutrition Modifier");
			this.addConfig(FoodConfigs.SERVER.saturationModifier, "Saturation Modifier");

			ITEMS.forEach(this::addItem);
		}

		private void addConfig(ModConfigSpec.ConfigValue<?> config, String name)
		{
			this.add(FoodMod.MODID + ".configuration." + config.getPath().getLast(), name);
		}

		public static MutableComponent shiftInfo(Object info)
		{
			return Component.translatable("tooltip." + FoodMod.MODID + ".hold_shift", info);
		}

		public static MutableComponent getFlavour(int flavour)
		{
			return Component.translatable("container." + FoodMod.MODID + ".popcorn_machine.flavour", flavour);
		}

		public static MutableComponent getPopcorn(int popcorn)
		{
			return Component.translatable("container." + FoodMod.MODID + ".popcorn_machine.popcorn", popcorn);
		}

		public static String capitalize(String id)
		{
			String[] names = id.split("_");
			StringBuilder builder = new StringBuilder();
			int i = 0;
			for (String name : names)
			{
				builder.append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
				i++;
				if (i != names.length)
					builder.append(" ");
			}
			return builder.toString();
		}
	}

	public static class Recipes extends RecipeProvider
	{
		public Recipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
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
			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CURDLER_WOODEN).pattern("s").pattern("s").pattern("g").define('g', ItemTags.GEARS_WOODEN).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("curdler").unlockedBy("criteria", has(ItemTags.GEARS_WOODEN)).save(recipeOutput);
			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CURDLER_STONE).pattern("s").pattern("s").pattern("g").define('g', ItemTags.GEARS_STONE).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("curdler").unlockedBy("criteria", has(ItemTags.GEARS_STONE)).save(recipeOutput);

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
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.BLOCK_OF_CHEESE.get(), 2).group("block_of_cheese").requires(Items.MILK_BUCKET).requires(HNCItemTags.CURDLERS).unlockedBy("has_milk", has(Items.MILK_BUCKET))
//					.unlockedBy("has_curdler", has(HNCItemTags.CURDLERS)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESE_SLICE.get(), 4).group("cheese_slice").requires(HNCBlocks.BLOCK_OF_CHEESE.get()).requires(ItemTag.TOOLS_KNIFE).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_CHEESE.get()))
//					.unlockedBy("has_knife", has(ItemTag.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.CHEESE_SLICE.get(), 5, Ingredient.of(HNCBlocks.BLOCK_OF_CHEESE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_CHEESE.get())).save(recipeOutput, FoodMod.id("cheese_slice_chopping_board"));
//
//			ShapelessRecipeBuilder.shapeless(ModItems.BLUE_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(HNCBlocks.BLOCK_OF_BLUE_CHEESE.get()).requires(ItemTag.TOOLS_KNIFE).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_BLUE_CHEESE.get()))
//					.unlockedBy("has_knife", has(ItemTag.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.BLUE_CHEESE_SLICE.get(), 5, Ingredient.of(HNCBlocks.BLOCK_OF_BLUE_CHEESE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_BLUE_CHEESE.get())).save(recipeOutput, FoodMod.id("blue_cheese_slice_chopping_board"));
//
//			ShapedRecipeBuilder.shaped(HNCBlocks.BLOCK_OF_GOUDA_CHEESE.get()).group("block_of_cheese").define('r', HNCItemTags.RED_DYES_COMMON).define('w', Items.HONEYCOMB).define('c', Ingredient.of(HNCBlocks.BLOCK_OF_CHEESE.get(), HNCBlocks.BLOCK_OF_GOAT_CHEESE.get())).pattern("wrw").pattern("rcr").pattern("wrw")
//					.unlockedBy("has_dye", has(HNCItemTags.RED_DYES_COMMON)).unlockedBy("has_wax", has(Items.HONEYCOMB)).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_CHEESE.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.GOUDA_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(HNCBlocks.BLOCK_OF_GOUDA_CHEESE.get()).requires(ItemTag.TOOLS_KNIFE).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_GOUDA_CHEESE.get()))
//					.unlockedBy("has_knife", has(ItemTag.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.GOUDA_CHEESE_SLICE.get(), 5, Ingredient.of(HNCBlocks.BLOCK_OF_GOUDA_CHEESE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_GOUDA_CHEESE.get())).save(recipeOutput, FoodMod.id("gouda_cheese_slice_chopping_board"));
//
//			ShapelessRecipeBuilder.shapeless(ModItems.SWISS_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(HNCBlocks.BLOCK_OF_SWISS_CHEESE.get()).requires(ItemTag.TOOLS_KNIFE).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_SWISS_CHEESE.get()))
//					.unlockedBy("has_knife", has(ItemTag.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.SWISS_CHEESE_SLICE.get(), 5, Ingredient.of(HNCBlocks.BLOCK_OF_SWISS_CHEESE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_SWISS_CHEESE.get())).save(recipeOutput, FoodMod.id("swiss_cheese_slice_chopping_board"));
//
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.BLOCK_OF_GOAT_CHEESE.get(), 2).group("block_of_cheese").requires(HNCFluids.GOAT_MILK_BUCKET.get()).requires(HNCItemTags.CURDLERS).unlockedBy("has_milk", has(HNCFluids.GOAT_MILK_BUCKET.get()))
//					.unlockedBy("has_curdler", has(HNCItemTags.CURDLERS)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.GOAT_CHEESE_SLICE.get(), 4).group("cheese_slice").requires(HNCBlocks.BLOCK_OF_GOAT_CHEESE.get()).requires(ItemTag.TOOLS_KNIFE).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_GOAT_CHEESE.get()))
//					.unlockedBy("has_knife", has(ItemTag.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.GOAT_CHEESE_SLICE.get(), 5, Ingredient.of(HNCBlocks.BLOCK_OF_GOAT_CHEESE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_cheese", has(HNCBlocks.BLOCK_OF_GOAT_CHEESE.get())).save(recipeOutput, FoodMod.id("goat_cheese_slice_chopping_board"));

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALT.get(), 2).requires(ModItems.GRIND_STONES.get()).requires(Tags.Items.STONES).unlockedBy("has_stone", has(Tags.Items.STONES)).unlockedBy("has_grind_stones", has(ModItems.GRIND_STONES.get())).save(recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FLOUR.get(), 3).requires(ModItems.GRIND_STONES.get()).requires(Tags.Items.CROPS_WHEAT).unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT)).unlockedBy("has_grind_stones", has(ModItems.GRIND_STONES.get())).save(recipeOutput);

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DOUGH.get(), 3).requires(Tags.Items.CROPS_WHEAT).requires(Items.SUGAR).requires(ItemTags.FOODS_SALT).requires(ItemTags.FOODS_FLOUR).unlockedBy("has_wheat", has(Tags.Items.CROPS_WHEAT)).unlockedBy("has_sugar", has(Items.SUGAR)).unlockedBy("has_salt", has(ItemTags.FOODS_SALT)).unlockedBy("has_flour", has(ItemTags.FOODS_FLOUR)).save(recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.UNFIRED_PIZZA_BASE.get()).requires(ItemTags.TOOLS_ROLLING_PIN).requires(ModItems.DOUGH.get()).unlockedBy("has_dough", has(ModItems.DOUGH.get())).unlockedBy("has_rolling_pin", has(ItemTags.TOOLS_ROLLING_PIN)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.UNBAKED_PIZZA_BASE.get(), Ingredient.of(ModItems.DOUGH.get())).tool(Ingredient.of(ItemTag.TOOLS_ROLLING_PIN)).unlockedBy("has_dough", has(ModItems.DOUGH.get())).save(recipeOutput, FoodMod.id("unbaked_pizza_base_chopping_board"));
			SpecialRecipeBuilder.special(PizzaRecipe::new).save(recipeOutput, FoodMod.id("pizza"));

			ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.UNBAKED_BREAD.get(), 2).define('d', ItemTags.FOODS_DOUGH).pattern("ddd").unlockedBy("has_dough", has(ItemTags.FOODS_DOUGH)).save(recipeOutput);
			SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItems.UNBAKED_BREAD.get()), RecipeCategory.FOOD, Items.BREAD, .2f, 200).unlockedBy("has_unbaked_bread", has(ModItems.UNBAKED_BREAD.get())).save(recipeOutput, FoodMod.id("bread_smoking"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BREAD_SLICE.get(), ModFoods.SLICE_PER_BREAD).requires(ItemTags.TOOLS_KNIFE).requires(Items.BREAD).unlockedBy("has_bread", has(Items.BREAD)).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE)).save(recipeOutput);
			this.cookedFood(ModItems.BREAD_SLICE, ModItems.TOAST, .35f, 200, recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.BREAD_SLICE.get(), 4, Ingredient.of(HNCItemTags.BREAD_COMMON)).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_bread", has(HNCItemTags.BREAD_COMMON)).save(recipeOutput, FoodMod.id("bread_slice_chopping_board"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MOLDY_BREAD_SLICE.get()).requires(Blocks.MOSS_BLOCK).requires(ModItems.BREAD_SLICE.get()).unlockedBy("has_moss", has(Blocks.MOSS_BLOCK)).unlockedBy("has_bread_slice", has(ModItems.BREAD_SLICE.get())).save(recipeOutput);

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.UNBAKED_CRACKER.get(), 3).requires(ModItems.UNFIRED_PIZZA_BASE).requires(ItemTags.TOOLS_KNIFE).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE)).save(recipeOutput);
			this.cookedFood(ModItems.UNBAKED_CRACKER.get(), ModItems.CRACKER.get(), .35f, 100, recipeOutput);
			SpecialRecipeBuilder.special(CrackerRecipe::new).save(recipeOutput, FoodMod.id("cracker_special"));

			this.cookedFood(ModItems.CRACKED_EGG.get(), ModItems.FRIED_EGG.get(), .15f, 100, recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_CRACKED_EGG.get()).requires(Tags.Items.DYES_GREEN).requires(ModItems.CRACKED_EGG.get()).unlockedBy("has_egg", has(ModItems.CRACKED_EGG.get())).unlockedBy("has_dye", has(Tags.Items.DYES_GREEN)).save(recipeOutput);

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HAM_SLICE.get(), 3).requires(ItemTags.TOOLS_KNIFE).requires(Items.PORKCHOP).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE))
					.unlockedBy("has_porkchop", has(Items.PORKCHOP)).save(recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COOKED_HAM_SLICE.get(), 3).requires(ItemTags.TOOLS_KNIFE).requires(Items.COOKED_PORKCHOP).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE))
					.unlockedBy("has_cooked_porkchop", has(Items.COOKED_PORKCHOP)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.HAM_SLICE.get(), 4, Ingredient.of(Items.PORKCHOP)).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_porkchop", has(Items.PORKCHOP)).save(recipeOutput, FoodMod.id("ham_slice_chopping_board"));
//			ChoppingBoardRecipeBuilder.recipe(ModItems.COOKED_HAM_SLICE.get(), 4, Ingredient.of(Items.COOKED_PORKCHOP)).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_porkchop", has(Items.COOKED_PORKCHOP)).save(recipeOutput, FoodMod.id("cooked_ham_slice_chopping_board"));
			this.cookedFood(ModItems.HAM_SLICE.get(), ModItems.COOKED_HAM_SLICE.get(), .35f, 200, recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_HAM_SLICE.get()).requires(Tags.Items.DYES_GREEN).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_ham", has(ModItems.HAM_SLICE.get())).unlockedBy("has_dye", has(Tags.Items.DYES_GREEN)).save(recipeOutput);

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RAW_BACON.get(), 2).requires(ItemTags.TOOLS_KNIFE).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_ham", has(ModItems.HAM_SLICE.get())).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE)).save(recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COOKED_BACON.get(), 2).requires(ItemTags.TOOLS_KNIFE).requires(ModItems.COOKED_HAM_SLICE.get()).unlockedBy("has_ham", has(ModItems.COOKED_HAM_SLICE.get())).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE)).save(recipeOutput, FoodMod.id("cooked_bacon_knife"));
//			ChoppingBoardRecipeBuilder.recipe(ModItems.RAW_BACON.get(), 3, Ingredient.of(ModItems.HAM_SLICE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_ham", has(ModItems.HAM_SLICE.get())).save(recipeOutput, FoodMod.id("bacon_chopping_board"));
//			ChoppingBoardRecipeBuilder.recipe(ModItems.COOKED_BACON.get(), 3, Ingredient.of(ModItems.COOKED_HAM_SLICE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_ham", has(ModItems.COOKED_HAM_SLICE.get())).save(recipeOutput, FoodMod.id("cooked_bacon_chopping_board"));
			this.cookedFood(ModItems.RAW_BACON.get(), ModItems.COOKED_BACON.get(), .2f, 100, recipeOutput);

			SpecialRecipeBuilder.special(SandwichRecipe::new).save(recipeOutput, FoodMod.id("sandwich"));

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_RING.get(), 4).requires(ItemTags.TOOLS_KNIFE).requires(ModItems.PINEAPPLE.get()).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE.get()))
					.unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE)).save(recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.PINEAPPLE_BITES.get(), 3).requires(ItemTags.TOOLS_KNIFE).requires(ModItems.PINEAPPLE_RING.get()).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE_RING.get()))
					.unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.PINEAPPLE_RING.get(), 5, Ingredient.of(ModItems.PINEAPPLE.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE.get())).save(recipeOutput, FoodMod.id("pineapple_ring_chopping_board"));
//			ChoppingBoardRecipeBuilder.recipe(ModItems.PINEAPPLE_BIT.get(), 4, Ingredient.of(ModItems.PINEAPPLE_RING.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_pineapple", has(ModItems.PINEAPPLE_RING.get())).save(recipeOutput, FoodMod.id("pineapple_bit_chopping_board"));

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_SAUCE.get()).requires(Items.BOWL).requires(Items.PUMPKIN_SEEDS).requires(ItemTags.FOODS_TOMATO).unlockedBy("has_bowl", has(Items.BOWL))
					.unlockedBy("has_pumpkin_seeds", has(Items.PUMPKIN_SEEDS)).unlockedBy("has_tomato", has(ItemTags.FOODS_TOMATO)).save(recipeOutput);
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_SLICE.get(), 4).requires(ItemTags.TOOLS_KNIFE).requires(ModItems.TOMATO.get()).unlockedBy("has_knife", has(ItemTags.TOOLS_KNIFE))
					.unlockedBy("has_tomato", has(ModItems.TOMATO.get())).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.TOMATO_SLICE.get(), 5, Ingredient.of(ModItems.TOMATO.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_tomato", has(ModItems.TOMATO.get())).save(recipeOutput, FoodMod.id("tomato_slice_chopping_board"));
//			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.TOMATO_SEEDS.get(), 4).requires(ModItems.TOMATO_SLICE.get()).unlockedBy("has_tomato_slice", has(ModItems.TOMATO_SLICE.get())).save(recipeOutput);

//			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CORN_KERNELS.get(), 8).requires(ItemTag.TOOLS_KNIFE).requires(ModItems.CORN_COB.get()).unlockedBy("has_knife", has(ItemTag.TOOLS_KNIFE))
//					.unlockedBy("has_corn_cob", has(HNCItemTags.CORN_COMMON)).save(recipeOutput);
//			ChoppingBoardRecipeBuilder.recipe(ModItems.CORN_KERNELS.get(), 9, Ingredient.of(ModItems.CORN_COB.get())).tool(Ingredient.of(ItemTag.TOOLS_KNIFE)).unlockedBy("has_corn_cob", has(ModItems.CORN_COB.get())).save(recipeOutput, FoodMod.id("corn_kernels_chopping_board"));
			this.cookedFood(ModItems.CORN_KERNELS.get(), ModItems.DRIED_CORN_KERNELS.get(), .2f, 100, recipeOutput);

			this.cookedFood(ModItems.MOUSE.get(), ModItems.COOKED_MOUSE.get(), .2f, 100, recipeOutput);

			ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.POPCORN_BAG.get()).define('p', Items.PAPER).define('r', Tags.Items.DYES_RED)
					.pattern("p p").pattern("rpr").unlockedBy("has_paper", has(Items.PAPER)).unlockedBy("has_red_dye", has(Tags.Items.DYES_RED)).save(recipeOutput);

//			PopcornRecipeBuilder.popcorn(ModItems.POPCORN.get(), 2, 50, Ingredient.of(ModItems.ROCK_SALT.get()))
//					.unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);
//			PopcornRecipeBuilder.popcorn(ModItems.CHEESY_POPCORN.get(), 75, Ingredient.of(ModItems.ROCK_SALT.get())).flavouring(Ingredient.of(HNCItemTags.CHEESE_SLICE_COMMON))
//					.unlockedBy("has_cheese_slice", has(HNCItemTags.CHEESE_SLICE_COMMON)).unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);
//			PopcornRecipeBuilder.popcorn(ModItems.CARAMEL_POPCORN.get(), 75, Ingredient.of(ModItems.ROCK_SALT.get())).flavouring(Ingredient.of(HNCItemTags.SUGAR_COMMON))
//					.unlockedBy("has_sugar", has(HNCItemTags.SUGAR_COMMON)).unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);
//			PopcornRecipeBuilder.popcorn(ModItems.MAPLE_POPCORN.get(), 75, Ingredient.of(ModItems.ROCK_SALT.get())).flavouring(Ingredient.of(ModItems.MAPLE_SYRUP.get()))
//					.unlockedBy("has_syrup", has(ModItems.MAPLE_SYRUP.get())).unlockedBy("has_salt", has(ModItems.ROCK_SALT.get())).save(recipeOutput);

//			SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.UNBAKED_CROISSANT.get()), ModItems.CROISSANT.get(), .2f, 100).unlockedBy("has_unbaked_croissant", has(ModItems.UNBAKED_CROISSANT.get())).save(recipeOutput);
//			SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItems.UNBAKED_CROISSANT.get()), ModItems.CROISSANT.get(), .2f, 50, RecipeSerializer.SMOKING_RECIPE).unlockedBy("has_unbaked_croissant", has(ModItems.UNBAKED_CROISSANT.get())).save(recipeOutput, FoodMod.id("croissant_smoking"));
//			SimpleCookingRecipeBuilder.cooking(Ingredient.of(ModItems.UNBAKED_CROISSANT.get()), ModItems.CROISSANT.get(), .2f, 300, RecipeSerializer.CAMPFIRE_COOKING_RECIPE).unlockedBy("has_unbaked_croissant", has(ModItems.UNBAKED_CROISSANT.get())).save(recipeOutput, FoodMod.id("croissant_campfire"));
//
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESY_CROISSANT.get()).group("croissant").requires(ModItems.CROISSANT.get()).requires(HNCItemTags.CHEESE_SLICE_COMMON).unlockedBy("has_croissant", has(ModItems.CROISSANT.get())).unlockedBy("has_cheese_slice", has(HNCItemTags.CHEESE_SLICE_COMMON)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESY_HAM_CROISSANT.get()).group("croissant").requires(ModItems.CHEESY_CROISSANT.get()).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_croissant", has(ModItems.CHEESY_CROISSANT.get())).unlockedBy("has_ham_slice", has(ModItems.HAM_SLICE.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(ModItems.CHEESY_HAM_CROISSANT.get()).group("croissant").requires(ModItems.CROISSANT.get()).requires(HNCItemTags.CHEESE_SLICE_COMMON).requires(ModItems.HAM_SLICE.get()).unlockedBy("has_croissant", has(ModItems.CROISSANT.get())).unlockedBy("has_cheese_slice", has(HNCItemTags.CHEESE_SLICE_COMMON)).unlockedBy("has_ham_slice", has(ModItems.HAM_SLICE.get())).save(recipeOutput, FoodMod.id("cheesy_croissant_with_ham_alt"));

			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MAPLE_SYRUP.get()).requires(ModItems.MAPLE_SAP_BOTTLE).requires(Items.SUGAR).unlockedBy("has_maple_sap", has(ModItems.MAPLE_SAP_BOTTLE)).save(recipeOutput);

			// Other
//			ShapedRecipeBuilder.shaped(HNCBlocks.PIZZA_OVEN.get()).define('b', Items.BRICK).define('t', Blocks.WHITE_TERRACOTTA).define('c', Ingredient.of(HNCItemTags.CAMPFIRES))
//					.pattern(" t ").pattern("tct").pattern("bbb").unlockedBy("has_bricks", has(Items.BRICK)).unlockedBy("has_terracotta", has(Blocks.WHITE_TERRACOTTA))
//					.unlockedBy("has_campfire", has(HNCItemTags.CAMPFIRES)).save(recipeOutput);
//
//			ShapedRecipeBuilder.shaped(HNCBlocks.GRILL.get()).define('i', HNCItemTags.IRON_INGOTS_COMMON).define('r', HNCItemTags.REDSTONE_COMMON).define('s', Blocks.SMOKER).define('b', Blocks.IRON_BARS)
//					.pattern("i i").pattern("rsi").pattern("b b").unlockedBy("has_iron", has(HNCItemTags.IRON_INGOTS_COMMON)).unlockedBy("has_redstone", has(HNCItemTags.REDSTONE_COMMON))
//					.unlockedBy("has_smoker", has(Blocks.SMOKER)).unlockedBy("has_bars", has(Blocks.IRON_BARS)).save(recipeOutput);
//
//			ShapedRecipeBuilder.shaped(HNCBlocks.POPCORN_MACHINE.get()).define('i', HNCItemTags.IRON_INGOTS_COMMON).define('r', Blocks.RED_CONCRETE).define('w', Blocks.WHITE_CONCRETE).define('b', Items.BUCKET).define('g', Blocks.GLASS_PANE)
//					.pattern("rwr").pattern("gbg").pattern("i i").unlockedBy("has_iron", has(HNCItemTags.IRON_INGOTS_COMMON)).unlockedBy("has_red_concrete", has(Blocks.RED_CONCRETE))
//					.unlockedBy("has_white_concrete", has(Blocks.WHITE_CONCRETE)).unlockedBy("has_bucket", has(Items.BUCKET)).unlockedBy("has_glass_pane", has(Blocks.GLASS_PANE)).save(recipeOutput);

//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_WOOD.get(), 3).define('l', HNCBlocks.MAPLE_LOG.get()).pattern("ll").pattern("ll").group("bark").unlockedBy("has_log", has(HNCBlocks.MAPLE_LOG.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.STRIPPED_MAPLE_WOOD.get(), 3).define('l', HNCBlocks.STRIPPED_MAPLE_LOG.get()).pattern("ll").pattern("ll").group("bark").unlockedBy("has_log", has(HNCBlocks.STRIPPED_MAPLE_LOG.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.MAPLE_PLANKS.get(), 4).requires(Ingredient.of(HNCItemTags.MAPLE_LOGS)).group("planks").unlockedBy("has_log", has(HNCItemTags.MAPLE_LOGS)).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_STAIRS.get(), 4).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("p  ").pattern("pp ").pattern("ppp").group("wooden_stairs").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_SLAB.get(), 6).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("ppp").group("wooden_slab").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_SIGN.getFirst().get(), 3).group("sign").define('p', HNCBlocks.MAPLE_PLANKS.get()).define('s', Items.STICK).pattern("ppp").pattern("ppp").pattern(" s ").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_PRESSURE_PLATE.get()).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("pp").group("wooden_pressure_plate").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.MAPLE_BUTTON.get()).requires(HNCBlocks.MAPLE_PLANKS.get()).group("wooden_button").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_FENCE.get(), 3).define('s', Items.STICK).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("psp").pattern("psp").group("wooden_fence").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_FENCE_GATE.get()).define('s', Items.STICK).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("sps").pattern("sps").group("wooden_fence_gate").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_TRAPDOOR.get(), 2).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("ppp").pattern("ppp").group("wooden_trapdoor").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(HNCBlocks.MAPLE_DOOR.get(), 3).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("pp").pattern("pp").pattern("pp").group("wooden_door").unlockedBy("has_planks", has(HNCBlocks.MAPLE_PLANKS.get())).save(recipeOutput);
//			ShapedRecipeBuilder.shaped(ModItems.MAPLE_BOAT.get()).define('p', HNCBlocks.MAPLE_PLANKS.get()).pattern("p p").pattern("ppp").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(recipeOutput);
//
//			ShapedRecipeBuilder.shaped(HNCBlocks.TREE_TAP.get()).define('i', HNCItemTags.IRON_INGOTS_COMMON).define('n', HNCItemTags.IRON_NUGGETS_COMMON).pattern(" n").pattern("ii").pattern(" n").unlockedBy("has_iron", has(HNCItemTags.IRON_INGOTS_COMMON)).unlockedBy("has_iron_nugget", has(HNCItemTags.IRON_NUGGETS_COMMON)).save(recipeOutput);
//
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.OAK_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.OAK_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.OAK_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.BIRCH_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.BIRCH_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.BIRCH_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.SPRUCE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.SPRUCE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.SPRUCE_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.JUNGLE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.JUNGLE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.JUNGLE_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.ACACIA_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.ACACIA_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.ACACIA_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.DARK_OAK_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.DARK_OAK_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.DARK_OAK_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.CRIMSON_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.CRIMSON_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.CRIMSON_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.WARPED_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.WARPED_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.WARPED_PRESSURE_PLATE)).save(recipeOutput);
//
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.STONE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.STONE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.STONE_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.POLISHED_BLACKSTONE_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.GOLD_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE)).save(recipeOutput);
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.IRON_CHOPPING_BOARD.get()).group("chopping_board").requires(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).unlockedBy("has_pressure_plate", has(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)).save(recipeOutput);
//
//			ShapelessRecipeBuilder.shapeless(HNCBlocks.MAPLE_CHOPPING_BOARD.get()).group("chopping_board").requires(HNCBlocks.MAPLE_PRESSURE_PLATE.get()).unlockedBy("has_pressure_plate", has(HNCBlocks.MAPLE_PRESSURE_PLATE.get())).save(recipeOutput);
//
//			ChoppingBoardRecipeBuilder.recipe(ModItems.UNBAKED_CROISSANT.get(), Ingredient.of(ModItems.UNBAKED_PIZZA_BASE.get())).tool(Ingredient.of(HNCItemTags.ROLLING_PINS)).unlockedBy("has_pizza_base", has(ModItems.UNBAKED_PIZZA_BASE.get())).save(recipeOutput);
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

	public static class BlockTags extends BlockTagsProvider
	{
		public static final TagKey<Block> MOUSE_SEARCHABLE = tag("mouse_searchable");

		public static final TagKey<Block> MINEABLE_WITH_KNIFE = tag("mineable/knife");

		public static final TagKey<Block> CHOPPING_BOARDS = tag("chopping_boards");

		public static final TagKey<Block> MAPLE_LOGS = tagCommon("maple_logs");

		public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
		{
			super(output, lookupProvider, FoodMod.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider)
		{
			this.tag(MOUSE_SEARCHABLE).addTags(Tags.Blocks.BARRELS, Tags.Blocks.CHESTS, net.minecraft.tags.BlockTags.SHULKER_BOXES).add(Blocks.DISPENSER, Blocks.DROPPER, Blocks.HOPPER);

			this.tag(MINEABLE_WITH_KNIFE);// cheese blocks

			this.tag(CHOPPING_BOARDS);// leaves

			this.tag(net.minecraft.tags.BlockTags.CROPS);// bricks

			this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).addTag(CHOPPING_BOARDS);
			this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE);// grill, pizza oven, popcorn machine, tree tap

			this.tag(net.minecraft.tags.BlockTags.PLANKS);// maple
			this.tag(net.minecraft.tags.BlockTags.WOODEN_BUTTONS);
			this.tag(net.minecraft.tags.BlockTags.WOODEN_DOORS);
			this.tag(net.minecraft.tags.BlockTags.WOODEN_STAIRS);
			this.tag(net.minecraft.tags.BlockTags.WOODEN_SLABS);
			this.tag(net.minecraft.tags.BlockTags.WOODEN_FENCES);
			this.tag(net.minecraft.tags.BlockTags.SAPLINGS);
			this.tag(MAPLE_LOGS);
			this.tag(net.minecraft.tags.BlockTags.LOGS).addTag(MAPLE_LOGS);
			this.tag(net.minecraft.tags.BlockTags.LOGS_THAT_BURN).addTag(MAPLE_LOGS);
			this.tag(net.minecraft.tags.BlockTags.FLOWER_POTS);
			this.tag(net.minecraft.tags.BlockTags.WOODEN_PRESSURE_PLATES);
			this.tag(net.minecraft.tags.BlockTags.LEAVES);
			this.tag(net.minecraft.tags.BlockTags.WOODEN_TRAPDOORS);
			this.tag(net.minecraft.tags.BlockTags.STANDING_SIGNS);
			this.tag(net.minecraft.tags.BlockTags.WALL_SIGNS);
			this.tag(net.minecraft.tags.BlockTags.CEILING_HANGING_SIGNS);
			this.tag(net.minecraft.tags.BlockTags.WALL_HANGING_SIGNS);
			this.tag(net.minecraft.tags.BlockTags.FENCE_GATES);
		}

		private static TagKey<Block> tag(String location)
		{
			return TagKey.create(Registries.BLOCK, FoodMod.id(location));
		}

		private static TagKey<Block> tagCommon(String location)
		{
			return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", location));
		}
	}

	public static class ItemTags extends ItemTagsProvider
	{
		// Crafting Materials
		public static final TagKey<Item> GEARS_WOODEN = tagCommon("gears/wooden");
		public static final TagKey<Item> GEARS_STONE = tagCommon("gears/stone");
		public static final TagKey<Item> GEARS = tagCommon("gears");

		// Tools
		public static final TagKey<Item> TOOLS_CURDLER = tagCommon("tools/curdler");
		public static final TagKey<Item> TOOLS_ROLLING_PIN = tagCommon("tools/rolling_pin");
		public static final TagKey<Item> TOOLS_KNIFE = tagCommon("tools/knife");

		// Foods
		public static final TagKey<Item> FOODS_CHEESE = tagCommon("foods/cheese");
		public static final TagKey<Item> FOODS_CHEESE_SLICE = tagCommon("foods/cheese/slice");

		public static final TagKey<Item> FOODS_SALT = tagCommon("foods/salt");

		public static final TagKey<Item> FOODS_FLOUR = tagCommon("foods/flour");

		public static final TagKey<Item> FOODS_DOUGH = tagCommon("foods/dough");

		public static final TagKey<Item> FOODS_UNFIRED_PIZZA_BASE = tag("foods/unfired_pizza_base");
		public static final TagKey<Item> FOODS_PIZZA = tagCommon("foods/pizza");

		public static final TagKey<Item> FOODS_BREAD_SLICE = tagCommon("foods/bread/slice");

		public static final TagKey<Item> FOODS_CRACKER = tagCommon("foods/cracker");

		public static final TagKey<Item> FOODS_EGG_CRACKED = tagCommon("foods/egg/cracked");

		public static final TagKey<Item> FOODS_HAM = tagCommon("foods/ham");
		public static final TagKey<Item> FOODS_BACON = tagCommon("foods/bacon");

		public static final TagKey<Item> FOODS_PINEAPPLE = tagCommon("foods/pineapple");

		public static final TagKey<Item> FOODS_TOMATO = tagCommon("foods/tomato");
		public static final TagKey<Item> FOODS_TOMATO_SAUCE = tagCommon("foods/tomato_sauce");

		public static final TagKey<Item> FOODS_CORN = tagCommon("foods/corn");

		public static final TagKey<Item> FOODS_POPCORN = tagCommon("foods/popcorn");

		public static final TagKey<Item> DRINKS_MAPLE_SAP = tagCommon("drinks/maple_sap");
		public static final TagKey<Item> DRINKS_MAPLE_SYRUP = tagCommon("drinks/maple_syrup");

		// Other
		public static final TagKey<Item> MOUSE_BLACKLIST = tagCommon("mouse_blacklist");

		public static final TagKey<Item> SEEDS_PINEAPPLE = tagCommon("seeds/pineapple");
		public static final TagKey<Item> SEEDS_TOMATO = tagCommon("seeds/tomato");
		public static final TagKey<Item> SEEDS_CORN = tagCommon("seeds/corn");

		public static final TagKey<Item> MAPLE_LOGS = tagCommon("maple_logs");

		public static final TagKey<Item> CHOPPING_BOARDS = tag("maple_logs");

		public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper)
		{
			super(output, lookupProvider, blockTagLookup, FoodMod.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider)
		{
			// Crafting Materials
			this.tag(GEARS_WOODEN).add(ModItems.GEAR_WOODEN.get());
			this.tag(GEARS_STONE).add(ModItems.GEAR_STONE.get());
			this.tag(GEARS).addTags(GEARS_WOODEN, GEARS_STONE);

			// Tools
			this.tag(TOOLS_CURDLER).add(ModItems.CURDLER_WOODEN.get(), ModItems.CURDLER_STONE.get());
			this.tag(TOOLS_ROLLING_PIN).add(ModItems.ROLLING_PIN_WOODEN.get(), ModItems.ROLLING_PIN_STONE.get());
			this.tag(TOOLS_KNIFE).add(ModItems.KNIFE_WOODEN.get(), ModItems.KNIFE_STONE.get(), ModItems.KNIFE_COPPER.get(), ModItems.KNIFE_GOLD.get(), ModItems.KNIFE_IRON.get(), ModItems.KNIFE_DIAMOND.get(), ModItems.KNIFE_NETHERITE.get());
			this.tag(Tags.Items.TOOLS).addTags(TOOLS_CURDLER, TOOLS_ROLLING_PIN, TOOLS_KNIFE);

			// Foods
			this.tag(FOODS_CHEESE_SLICE).add(ModItems.CHEESE_SLICE.get(), ModItems.BLUE_CHEESE_SLICE.get(), ModItems.GOUDA_CHEESE_SLICE.get(), ModItems.SWISS_CHEESE_SLICE.get(), ModItems.GOAT_CHEESE_SLICE.get());
			this.tag(FOODS_CHEESE).addTag(FOODS_CHEESE_SLICE).add(ModItems.SWISS_CHEESE_BITES.get());

			this.tag(FOODS_SALT).add(ModItems.SALT.get());

			this.tag(FOODS_FLOUR).add(ModItems.FLOUR.get());

			this.tag(FOODS_DOUGH).add(ModItems.DOUGH.get(), ModItems.UNFIRED_PIZZA_BASE.get(), ModItems.UNBAKED_CRACKER.get());

			this.tag(FOODS_UNFIRED_PIZZA_BASE).add(ModItems.UNFIRED_PIZZA_BASE.get());
			this.tag(FOODS_PIZZA).add(ModItems.UNFIRED_PIZZA_BASE.get(), ModItems.FIRED_PIZZA_BASE.get(), ModItems.PIZZA.get());

			this.tag(FOODS_BREAD_SLICE).add(ModItems.BREAD_SLICE.get(), ModItems.TOAST.get(), ModItems.MOLDY_BREAD_SLICE.get());
//			this.tag(Tags.Items.FOODS_BREAD).addTag(FOODS_BREAD_SLICE);

			this.tag(FOODS_CRACKER).add(ModItems.CRACKER.get());

			this.tag(FOODS_EGG_CRACKED).add(ModItems.CRACKED_EGG.get(), ModItems.FRIED_EGG.get(), ModItems.GREEN_CRACKED_EGG.get());
			this.tag(Tags.Items.EGGS).addTag(FOODS_EGG_CRACKED);

			this.tag(FOODS_HAM).add(ModItems.HAM_SLICE.get(), ModItems.COOKED_HAM_SLICE.get(), ModItems.GREEN_HAM_SLICE.get());
			this.tag(FOODS_BACON).add(ModItems.RAW_BACON.get(), ModItems.COOKED_BACON.get());

			this.tag(FOODS_PINEAPPLE).add(ModItems.PINEAPPLE.get(), ModItems.PINEAPPLE_RING.get(), ModItems.PINEAPPLE_BITES.get());

			this.tag(FOODS_TOMATO).add(ModItems.TOMATO.get(), ModItems.TOMATO_SLICE.get());
			this.tag(FOODS_TOMATO_SAUCE).add(ModItems.TOMATO_SAUCE.get());

			this.tag(FOODS_CORN).add(ModItems.CORN_KERNELS.get());

			this.tag(FOODS_POPCORN).add(ModItems.POPCORN.get(), ModItems.CHEESY_POPCORN.get(), ModItems.CARAMEL_POPCORN.get(), ModItems.MAPLE_POPCORN.get());

			this.tag(Tags.Items.FOODS).addTags(FOODS_CHEESE, FOODS_SALT, FOODS_FLOUR, FOODS_DOUGH, FOODS_PIZZA, FOODS_CRACKER, FOODS_EGG_CRACKED, FOODS_HAM, FOODS_BACON, FOODS_PINEAPPLE, FOODS_TOMATO, FOODS_TOMATO_SAUCE, FOODS_CORN, FOODS_POPCORN);

			this.tag(DRINKS_MAPLE_SAP).add(ModItems.MAPLE_SAP_BOTTLE.get());
			this.tag(DRINKS_MAPLE_SYRUP).add(ModItems.MAPLE_SYRUP.get());
			this.tag(Tags.Items.DRINKS).addTags(DRINKS_MAPLE_SAP, DRINKS_MAPLE_SYRUP);

			// Other
			this.tag(MOUSE_BLACKLIST).add(Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.PUFFERFISH, ModItems.MOUSE.get(), ModItems.COOKED_MOUSE.get(), ModItems.FOOD_SCRAPS.get());

			this.tag(SEEDS_PINEAPPLE);
			this.tag(SEEDS_TOMATO);
			this.tag(SEEDS_CORN);
			this.tag(Tags.Items.SEEDS).addTags(SEEDS_PINEAPPLE, SEEDS_TOMATO, SEEDS_CORN);

			this.copy(BlockTags.MAPLE_LOGS, MAPLE_LOGS);

			this.copy(BlockTags.CHOPPING_BOARDS, CHOPPING_BOARDS);
		}

		private static TagKey<Item> tag(String location)
		{
			return TagKey.create(Registries.ITEM, FoodMod.id(location));
		}

		private static TagKey<Item> tagCommon(String location)
		{
			return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", location));
		}
	}
}
