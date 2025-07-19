package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class ModItemTags extends ItemTagsProvider
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

	public ModItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper)
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

		this.tag(SEEDS_PINEAPPLE).add(ModItems.PINEAPPLE_CROWN.get());
		this.tag(SEEDS_TOMATO).add(ModItems.TOMATO_SEEDS.get());
		this.tag(SEEDS_CORN);
		this.tag(Tags.Items.SEEDS).addTags(SEEDS_PINEAPPLE, SEEDS_TOMATO, SEEDS_CORN);

		this.copy(ModBlockTags.MAPLE_LOGS, MAPLE_LOGS);

		this.copy(ModBlockTags.CHOPPING_BOARDS, CHOPPING_BOARDS);
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
