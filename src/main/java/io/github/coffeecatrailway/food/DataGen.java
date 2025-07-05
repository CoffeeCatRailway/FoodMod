package io.github.coffeecatrailway.food;

import io.github.coffeecatrailway.food.common.ModFoods;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.crafting.SandwichRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
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
			this.basicItem(ModItems.BREAD_SLICE.get());
			this.basicItem(ModItems.TOAST.get());

			this.basicItem(ModItems.KNIFE_WOODEN.get());
			this.basicItem(ModItems.KNIFE_STONE.get());
			this.basicItem(ModItems.KNIFE_COPPER.get());
			this.basicItem(ModItems.KNIFE_GOLD.get());
			this.basicItem(ModItems.KNIFE_IRON.get());
			this.basicItem(ModItems.KNIFE_DIAMOND.get());
			this.basicItem(ModItems.KNIFE_NETHERITE.get());
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
			this.add(ModItems.BREAD_SLICE.get(), "Bread Slice");
			this.add(ModItems.TOAST.get(), "Toast");

			this.add(ModItems.KNIFE_WOODEN.get(), "Wooden Knife");
			this.add(ModItems.KNIFE_STONE.get(), "Stone Knife");
			this.add(ModItems.KNIFE_COPPER.get(), "Copper Knife");
			this.add(ModItems.KNIFE_GOLD.get(), "Gold Knife");
			this.add(ModItems.KNIFE_IRON.get(), "Iron Knife");
			this.add(ModItems.KNIFE_DIAMOND.get(), "Diamond Knife");
			this.add(ModItems.KNIFE_NETHERITE.get(), "Netherite Knife");

			this.add("item." + FoodMod.MODID + ".food_combo.info", "to show ingredients");
			this.add("item." + FoodMod.MODID + ".food_combo.info.nutrition", "Nutrition: %s");
			this.add("item." + FoodMod.MODID + ".food_combo.info.saturation", "Saturation: %s");
			this.add(ModItems.SANDWICH.get(), "Sandwich");
			this.add("item." + FoodMod.MODID + ".sandwich.toasted", "Toasted");

			this.add("tooltip." + FoodMod.MODID + ".hold_shift", "[HOLD SHIFT %s]");
		}

		public static MutableComponent shiftInfo(Object info)
		{
			return Component.translatable( "tooltip." + FoodMod.MODID + ".hold_shift", info);
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
			ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BREAD_SLICE, ModFoods.SLICE_PER_BREAD).requires(Items.BREAD).requires(ItemTag.KNIFES).unlockedBy("has_bread", has(Items.BREAD)).save(recipeOutput);

			this.cookedFood(ModItems.BREAD_SLICE, ModItems.TOAST, .35f, 200, recipeOutput);

			SpecialRecipeBuilder.special(SandwichRecipe::new).save(recipeOutput, FoodMod.id("sandwich"));

			this.knife(ModItems.KNIFE_WOODEN, ItemTags.PLANKS, recipeOutput);
			this.knife(ModItems.KNIFE_STONE, Items.STONE, recipeOutput);
			this.knife(ModItems.KNIFE_COPPER, Items.COPPER_INGOT, recipeOutput);
			this.knife(ModItems.KNIFE_GOLD, Items.GOLD_INGOT, recipeOutput);
			this.knife(ModItems.KNIFE_IRON, Items.IRON_INGOT, recipeOutput);
			this.knife(ModItems.KNIFE_DIAMOND, Items.DIAMOND, recipeOutput);
			SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ModItems.KNIFE_DIAMOND), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.TOOLS, ModItems.KNIFE_NETHERITE.get()).unlocks("has_netherite", has(Items.NETHERITE_INGOT)).save(recipeOutput, FoodMod.id("knife_netherite"));
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
			return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).pattern("m").pattern("s").define('s', Items.STICK).showNotification(true).group("knife");
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
		public static final TagKey<Item> KNIFES = TagKey.create(Registries.ITEM, FoodMod.id("knifes"));
		public static final TagKey<Item> BREAD_SLICE = TagKey.create(Registries.ITEM, FoodMod.id("bread_slice"));

		public ItemTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper)
		{
			super(output, lookupProvider, blockTagLookup, FoodMod.MODID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider)
		{
			this.tag(KNIFES).add(ModItems.KNIFE_WOODEN.get(), ModItems.KNIFE_STONE.get(), ModItems.KNIFE_COPPER.get(), ModItems.KNIFE_GOLD.get(), ModItems.KNIFE_IRON.get(), ModItems.KNIFE_DIAMOND.get(), ModItems.KNIFE_NETHERITE.get());
			this.tag(BREAD_SLICE).add(ModItems.BREAD_SLICE.get(), ModItems.TOAST.get());
		}
	}
}
