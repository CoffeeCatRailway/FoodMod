package io.github.coffeecatrailway.food;

import io.github.coffeecatrailway.food.common.ModFoods;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.crafting.SandwichRecipe;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

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
			this.basicItem(ModItems.BREAD_SLICE.get());
			this.basicItem(ModItems.TOAST.get());
		}
	}

	public static class Language extends LanguageProvider
	{
		public Language(PackOutput output)
		{
			super(output, FoodMod.MODID, "en_us");
		}

		@Override
		protected void addTranslations()
		{
			// Crafting Materials
			this.add(ModItems.GEAR_WOODEN.get(), "Wooden Gear");
			this.add(ModItems.GEAR_STONE.get(), "Stone Gear");

			// Tools
			this.add(ModItems.CURDLER_WOODEN.get(), "Wooden Curdler");
			this.add(ModItems.CURDLER_STONE.get(), "Stone Curdler");

			this.add(ModItems.ROLLING_PIN_WOODEN.get(), "Wooden Rolling Pin");
			this.add(ModItems.ROLLING_PIN_STONE.get(), "Stone Rolling Pin");

			this.add(ModItems.GRIND_STONES.get(), "Grind Stones");

			this.add(ModItems.KNIFE_WOODEN.get(), "Wooden Knife");
			this.add(ModItems.KNIFE_STONE.get(), "Stone Knife");
			this.add(ModItems.KNIFE_COPPER.get(), "Copper Knife");
			this.add(ModItems.KNIFE_GOLD.get(), "Gold Knife");
			this.add(ModItems.KNIFE_IRON.get(), "Iron Knife");
			this.add(ModItems.KNIFE_DIAMOND.get(), "Diamond Knife");
			this.add(ModItems.KNIFE_NETHERITE.get(), "Netherite Knife");

			// Foods
			this.add(ModItems.BREAD_SLICE.get(), "Bread Slice");
			this.add(ModItems.TOAST.get(), "Toast");

			this.add("item." + FoodMod.MODID + ".food_combo.info", "to show ingredients");
			this.add("item." + FoodMod.MODID + ".food_combo.info.nutrition", "Nutrition: %s");
			this.add("item." + FoodMod.MODID + ".food_combo.info.saturation", "Saturation: %s");
			this.add(ModItems.SANDWICH.get(), "Sandwich");
			this.add("item." + FoodMod.MODID + ".sandwich.toasted", "Toasted");

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
		}

		private void addConfig(ModConfigSpec.ConfigValue<?> config, String name)
		{
			this.add(FoodMod.MODID + ".configuration." + config.getPath().getLast(), name);
		}

		public static MutableComponent shiftInfo(Object info)
		{
			return Component.translatable("tooltip." + FoodMod.MODID + ".hold_shift", info);
		}
	}

	public static class Recipe extends RecipeProvider
	{
		public Recipe(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
		{
			super(output, lookupProvider);
		}

		@Override
		protected void buildRecipes(RecipeOutput recipeOutput)
		{
			// Crafting Materials
			ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEAR_WOODEN).pattern(" s ").pattern("sms").pattern(" s ").define('m', ItemTags.PLANKS).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("gear").unlockedBy("criteria", has(ItemTags.PLANKS)).save(recipeOutput);
			ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GEAR_STONE).pattern(" s ").pattern("sms").pattern(" s ").define('m', Tags.Items.STONES).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("gear").unlockedBy("criteria", has(Tags.Items.STONES)).save(recipeOutput);

			// Tools
			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CURDLER_WOODEN).pattern("s").pattern("s").pattern("g").define('g', ItemTag.GEARS_WOODEN).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("curdler").unlockedBy("criteria", has(ItemTag.GEARS_WOODEN)).save(recipeOutput);
			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.CURDLER_STONE).pattern("s").pattern("s").pattern("g").define('g', ItemTag.GEARS_STONE).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("curdler").unlockedBy("criteria", has(ItemTag.GEARS_STONE)).save(recipeOutput);

			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ROLLING_PIN_WOODEN).pattern("sms").define('m', ItemTags.PLANKS).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("rolling_pin").unlockedBy("criteria", has(ItemTags.PLANKS)).save(recipeOutput);
			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ROLLING_PIN_STONE).pattern("sms").define('m', Tags.Items.STONES).define('s', Tags.Items.RODS_WOODEN)
					.showNotification(false).group("rolling_pin").unlockedBy("criteria", has(Tags.Items.STONES)).save(recipeOutput);

			ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.GRIND_STONES).requires(Items.COBBLESTONE).unlockedBy("has_stone", has(Items.COBBLESTONE)).save(recipeOutput);

			this.knife(ModItems.KNIFE_WOODEN, ItemTags.PLANKS, recipeOutput);
			this.knife(ModItems.KNIFE_STONE, Tags.Items.STONES, recipeOutput);
			this.knife(ModItems.KNIFE_COPPER, Items.COPPER_INGOT, recipeOutput);
			this.knife(ModItems.KNIFE_GOLD, Tags.Items.INGOTS_GOLD, recipeOutput);
			this.knife(ModItems.KNIFE_IRON, Tags.Items.INGOTS_IRON, recipeOutput);
			this.knife(ModItems.KNIFE_DIAMOND, Tags.Items.GEMS_DIAMOND, recipeOutput);
			SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.KNIFE_DIAMOND), Ingredient.of(Tags.Items.INGOTS_NETHERITE), RecipeCategory.TOOLS, ModItems.KNIFE_NETHERITE.get())
					.unlocks("has_netherite", has(Tags.Items.INGOTS_NETHERITE)).save(recipeOutput, FoodMod.id("knife_netherite"));

			// Foods
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BREAD_SLICE, ModFoods.SLICE_PER_BREAD).requires(Items.BREAD).requires(ItemTag.TOOLS_KNIFE).unlockedBy("has_bread", has(Items.BREAD)).save(recipeOutput);

			this.cookedFood(ModItems.BREAD_SLICE, ModItems.TOAST, .35f, 200, recipeOutput);

			SpecialRecipeBuilder.special(SandwichRecipe::new).save(recipeOutput, FoodMod.id("sandwich"));

			// Other
		}

		private void cookedFood(ItemLike raw, ItemLike cooked, float experience, int baseCookTime, RecipeOutput recipeOutput)
		{
			SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(raw), RecipeCategory.FOOD, cooked, experience, baseCookTime * 3).unlockedBy("has_raw", has(raw)).save(recipeOutput, FoodMod.id(getItemName(cooked) + "_campfire"));
			SimpleCookingRecipeBuilder.smelting(Ingredient.of(raw), RecipeCategory.FOOD, cooked, experience, baseCookTime).unlockedBy("has_raw", has(raw)).save(recipeOutput);
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

	public static class BlockTag extends BlockTagsProvider
	{
		public BlockTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
		{
			super(output, lookupProvider, FoodMod.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider)
		{
		}
	}

	public static class ItemTag extends ItemTagsProvider
	{
		// Crafting Materials
		public static final TagKey<Item> GEARS_WOODEN = tagCommon("gears/wooden");
		public static final TagKey<Item> GEARS_STONE = tagCommon("gears/stone");
		public static final TagKey<Item> GEARS = tagCommon("gears");

		// Tools
		public static final TagKey<Item> TOOLS_CURDLER = tag("tools/curdler");
		public static final TagKey<Item> TOOLS_ROLLING_PIN = tag("tools/rolling_pin");
		public static final TagKey<Item> TOOLS_KNIFE = tag("tools/knife");

		// Foods
		public static final TagKey<Item> BREAD_SLICE = TagKey.create(Registries.ITEM, FoodMod.id("bread_slice"));

		public ItemTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper)
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
			this.tag(BREAD_SLICE).add(ModItems.BREAD_SLICE.get(), ModItems.TOAST.get());
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
