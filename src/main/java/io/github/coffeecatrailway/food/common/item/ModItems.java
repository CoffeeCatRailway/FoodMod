package io.github.coffeecatrailway.food.common.item;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.ModFoods;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.datagen.LanguageGenerator;
import io.github.coffeecatrailway.food.datagen.ModBlockTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 27/05/2025
 */
public class ModItems
{
	private static final String ID_NAME = "ID";

	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FoodMod.MODID);

	// Crafting Materials
	public static final DeferredItem<Item> GEAR_WOODEN = register("gear_wooden", Item::new, "Wooden Gear");
	public static final DeferredItem<Item> GEAR_STONE = register("gear_stone", Item::new, "Stone Gear");

	// Tools
	public static final DeferredItem<CraftingToolItem> CURDLER_WOODEN = register("curdler_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 2f, -2.5f), "Wooden Curdler");
	public static final DeferredItem<CraftingToolItem> CURDLER_STONE = register("curdler_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 2f, -2.5f), "Stone Curdler");

	public static final DeferredItem<CraftingToolItem> ROLLING_PIN_WOODEN = register("rolling_pin_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 1f, -2.5f), "Wooden Rolling Pin");
	public static final DeferredItem<CraftingToolItem> ROLLING_PIN_STONE = register("rolling_pin_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 1f, -2.5f), "Wooden Rolling Pin");

	public static final DeferredItem<CraftingToolItem> GRIND_STONES = register("grind_stones", prop -> itemCraftingTool(prop, Tiers.STONE, 2f, -2.0f), ID_NAME);

	public static final DeferredItem<CraftingToolItem> KNIFE_WOODEN = register("knife_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Wooden Knife");
	public static final DeferredItem<CraftingToolItem> KNIFE_STONE = register("knife_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Stone Knife");
	public static final DeferredItem<CraftingToolItem> KNIFE_COPPER = register("knife_copper", prop -> itemCraftingTool(prop, Tiers.STONE, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Copper Knife");
	public static final DeferredItem<CraftingToolItem> KNIFE_GOLD = register("knife_gold", prop -> itemCraftingTool(prop, Tiers.GOLD, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Gold Knife");
	public static final DeferredItem<CraftingToolItem> KNIFE_IRON = register("knife_iron", prop -> itemCraftingTool(prop, Tiers.IRON, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Iron Knife");
	public static final DeferredItem<CraftingToolItem> KNIFE_DIAMOND = register("knife_diamond", prop -> itemCraftingTool(prop, Tiers.DIAMOND, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Diamond Knife");
	public static final DeferredItem<CraftingToolItem> KNIFE_NETHERITE = register("knife_netherite", prop -> itemCraftingTool(prop, Tiers.NETHERITE, 1f, -2.5f, ModBlockTags.MINEABLE_WITH_KNIFE), "Netherite Knife");

	// Foods
	public static final DeferredItem<Item> CHEESE_SLICE = register("cheese_slice", prop -> new Item(prop.food(ModFoods.CHEESE_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> BLUE_CHEESE_SLICE = register("blue_cheese_slice", prop -> new Item(prop.food(ModFoods.BLUE_CHEESE_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> GOUDA_CHEESE_SLICE = register("gouda_cheese_slice", prop -> new Item(prop.food(ModFoods.GOUDA_CHEESE_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> SWISS_CHEESE_SLICE = register("swiss_cheese_slice", prop -> new Item(prop.food(ModFoods.SWISS_CHEESE_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> SWISS_CHEESE_BITES = register("swiss_cheese_bites", prop -> new Item(prop.food(ModFoods.SWISS_CHEESE_BITES)), ID_NAME);
	public static final DeferredItem<Item> GOAT_CHEESE_SLICE = register("goat_cheese_slice", prop -> new Item(prop.food(ModFoods.GOAT_CHEESE_SLICE).stacksTo(32)), ID_NAME);

	public static final DeferredItem<Item> SALT = register("salt", prop -> new Item(prop.food(ModFoods.GENERAL_INGREDIENT)), ID_NAME);
	public static final DeferredItem<Item> FLOUR = register("flour", prop -> new Item(prop.food(ModFoods.GENERAL_INGREDIENT)), ID_NAME);

	public static final DeferredItem<Item> DOUGH = register("dough", prop -> new Item(prop.food(ModFoods.DOUGH)), ID_NAME);

	public static final DeferredItem<Item> UNFIRED_PIZZA_BASE = register("unfired_pizza_base", prop -> new Item(prop.food(ModFoods.DOUGH).stacksTo(16)), ID_NAME);
	public static final DeferredItem<Item> FIRED_PIZZA_BASE = register("fired_pizza_base", prop -> new Item(prop.food(ModFoods.PIZZA).stacksTo(16)), ID_NAME);
	public static final DeferredItem<PizzaItem> PIZZA = register("pizza", PizzaItem::new, ID_NAME);

	public static final DeferredItem<Item> UNBAKED_BREAD = register("unbaked_bread", prop -> new Item(prop.food(ModFoods.DOUGH)), ID_NAME);
	public static final DeferredItem<Item> BREAD_SLICE = register("bread_slice", prop -> new Item(prop.food(ModFoods.BREAD_SLICE)), ID_NAME);
	public static final DeferredItem<Item> TOAST = register("toast", prop -> new Item(prop.food(ModFoods.TOAST)), ID_NAME);
	public static final DeferredItem<Item> MOLDY_BREAD_SLICE = register("moldy_bread_slice", prop -> new Item(prop.food(ModFoods.MOLDY_BREAD_SLICE)), ID_NAME);

	public static final DeferredItem<Item> UNBAKED_CRACKER = register("unbaked_cracker", prop -> new Item(prop.food(ModFoods.DOUGH).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> CRACKER_DUMMY = register("cracker_dummy", prop -> new Item(prop.food(ModFoods.CRACKER)), ID_NAME);
	public static final DeferredItem<CrackerItem> CRACKER = register("cracker", CrackerItem::new, ID_NAME);

	public static final DeferredItem<Item> CRACKED_EGG = register("cracked_egg", prop -> new Item(prop.food(ModFoods.CRACKED_EGG).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> FRIED_EGG = register("fried_egg", prop -> new Item(prop.food(ModFoods.FRIED_EGG).stacksTo(32)), ID_NAME);
	public static final DeferredItem<GreenFoodItem> GREEN_CRACKED_EGG = register("green_cracked_egg", prop -> new GreenFoodItem(prop.food(ModFoods.GREEN_CRACKED_EGG).stacksTo(32)), ID_NAME);

	public static final DeferredItem<Item> HAM_SLICE = register("ham_slice", prop -> new Item(prop.food(ModFoods.HAM_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> COOKED_HAM_SLICE = register("cooked_ham_slice", prop -> new Item(prop.food(ModFoods.COOKED_HAM_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<GreenFoodItem> GREEN_HAM_SLICE = register("green_ham_slice", prop -> new GreenFoodItem(prop.food(ModFoods.GREEN_HAM_SLICE).stacksTo(32)), ID_NAME);

	public static final DeferredItem<Item> RAW_BACON = register("raw_bacon", prop -> new Item(prop.food(ModFoods.RAW_BACON).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> COOKED_BACON = register("cooked_bacon", prop -> new Item(prop.food(ModFoods.COOKED_BACON).stacksTo(32)), ID_NAME);

	public static final DeferredItem<SandwichItem> SANDWICH = register("sandwich", SandwichItem::new, ID_NAME);

	public static final DeferredItem<ItemNameBlockItem> PINEAPPLE_PLANT = register("pineapple_plant", prop -> new ItemNameBlockItem(ModBlocks.PINEAPPLE_PLANT.get(), prop), ID_NAME);
	public static final DeferredItem<Item> PINEAPPLE = register("pineapple", prop -> new Item(prop.food(ModFoods.PINEAPPLE).stacksTo(16)), ID_NAME);
	public static final DeferredItem<Item> PINEAPPLE_RING = register("pineapple_ring", prop -> new Item(prop.food(ModFoods.PINEAPPLE_RING).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> PINEAPPLE_BITES = register("pineapple_bites", prop -> new Item(prop.food(ModFoods.PINEAPPLE_BITES)), ID_NAME);

//	public static final DeferredItem<ItemNameBlockItem> TOMATO_SEEDS = register("tomato_seeds", prop -> new ItemNameBlockItem(ModBlocks.TOMATO_PLANT.get(), prop), ID_NAME);
	public static final DeferredItem<Item> TOMATO = register("tomato", prop -> new Item(prop.food(ModFoods.TOMATO)), ID_NAME);
	public static final DeferredItem<Item> TOMATO_SLICE = register("tomato_slice", prop -> new Item(prop.food(ModFoods.TOMATO_SLICE).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> TOMATO_SAUCE = register("tomato_sauce", prop -> new Item(prop.food(ModFoods.TOMATO_SAUCE).stacksTo(1)), ID_NAME);

//	public static final DeferredItem<ItemNameBlockItem> CORN_COB = register("corn_cob", prop -> new ItemNameBlockItem(ModBlocks.CORN_PLANT.get(), prop.food(ModFoods.CORN_COB).stacksTo(32)), ID_NAME);
	public static final DeferredItem<Item> CORN_KERNELS = register("corn_kernels", prop -> new Item(prop.food(ModFoods.CORN_KERNELS)), ID_NAME);
	public static final DeferredItem<Item> DRIED_CORN_KERNELS = register("dried_corn_kernels", prop -> new Item(prop.food(ModFoods.CORN_KERNELS)), ID_NAME);

	public static final DeferredItem<Item> POPCORN_BAG = register("popcorn_bag", Item::new, ID_NAME);
	public static final DeferredItem<PopcornItem> POPCORN = register("popcorn", prop -> new PopcornItem(prop.food(ModFoods.POPCORN)), ID_NAME);
	public static final DeferredItem<PopcornItem> CHEESY_POPCORN = register("cheesy_popcorn", prop -> new PopcornItem(prop.food(ModFoods.CHEESY_POPCORN)), ID_NAME);
	public static final DeferredItem<PopcornItem> CARAMEL_POPCORN = register("caramel_popcorn", prop -> new PopcornItem(prop.food(ModFoods.CARAMEL_POPCORN)), ID_NAME);
	public static final DeferredItem<PopcornItem> MAPLE_POPCORN = register("maple_popcorn", prop -> new PopcornItem(prop.food(ModFoods.MAPLE_POPCORN)), ID_NAME);

	public static final DeferredItem<Item> MOUSE = register("mouse", prop -> new Item(prop.food(ModFoods.MOUSE)), ID_NAME);
	public static final DeferredItem<Item> COOKED_MOUSE = register("cooked_mouse", prop -> new Item(prop.food(ModFoods.COOKED_MOUSE)), ID_NAME);

	public static final DeferredItem<Item> FOOD_SCRAPS = register("food_scraps", prop -> new Item(prop.food(ModFoods.FOOD_SCRAPS)), ID_NAME);

	public static final DeferredItem<FoodBottleItem> MAPLE_SAP_BOTTLE = register("maple_sap_bottle", prop -> new FoodBottleItem(prop.food(ModFoods.MAPLE_SAP_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)), ID_NAME);
	public static final DeferredItem<FoodBottleItem> MAPLE_SYRUP = register("maple_syrup", prop -> new FoodBottleItem(prop.food(ModFoods.MAPLE_SYRUP).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1)), ID_NAME);

	// TODO: Croissant

	// Other
	// TODO: Maple Boat

	private static CraftingToolItem itemCraftingTool(Item.Properties prop, Tier tier, float attackDamage, float attackSpeed, TagKey<Block> mineable)
	{
		return itemCraftingTool(prop.component(DataComponents.TOOL, tier.createToolProperties(mineable)), tier, attackDamage, attackSpeed);
	}

	private static CraftingToolItem itemCraftingTool(Item.Properties prop, Tier tier, float attackDamage, float attackSpeed)
	{
		return new CraftingToolItem(tier, prop.durability(tier.getUses() / 2).attributes(CraftingToolItem.createAttributes(tier, attackDamage, attackSpeed)));
	}

	private static <T extends Item> DeferredItem<T> register(String id, Function<Item.Properties, T> factory, String name)
	{
		DeferredItem<T> item = ITEMS.registerItem(id, factory);
		LanguageGenerator.ITEMS.put(item, name.equals(ID_NAME) ? LanguageGenerator.capitalize(id) : name);
		return item;
	}

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering items");
		ModItems.ITEMS.register(modEventBus);

		modEventBus.addListener(ModItems::addCreative);
	}

	private static void addCreative(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
		{
			LOGGER.debug("Adding to Tools And Utilities tab");
			final ItemStack WOODEN_HOE = new ItemStack(Items.WOODEN_HOE);
			final ItemStack STONE_HOE = new ItemStack(Items.STONE_HOE);
			final ItemStack GOLDEN_HOE = new ItemStack(Items.GOLDEN_HOE);
			final ItemStack IRON_HOE = new ItemStack(Items.IRON_HOE);
			final ItemStack DIAMOND_HOE = new ItemStack(Items.DIAMOND_HOE);
			final ItemStack NETHERITE_HOE = new ItemStack(Items.NETHERITE_HOE);

			event.insertAfter(WOODEN_HOE, new ItemStack(CURDLER_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(CURDLER_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(WOODEN_HOE, new ItemStack(ROLLING_PIN_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(ROLLING_PIN_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(WOODEN_HOE, new ItemStack(KNIFE_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(KNIFE_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(GOLDEN_HOE, new ItemStack(KNIFE_GOLD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(IRON_HOE, new ItemStack(KNIFE_IRON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(DIAMOND_HOE, new ItemStack(KNIFE_DIAMOND.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(NETHERITE_HOE, new ItemStack(KNIFE_NETHERITE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.accept(GRIND_STONES);
			event.accept(KNIFE_COPPER);
		}

		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
		{
			LOGGER.debug("Adding to Food And Drinks tab");
			event.insertBefore(new ItemStack(Items.BREAD), new ItemStack(DOUGH.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(new ItemStack(DOUGH.get()), new ItemStack(UNFIRED_PIZZA_BASE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(UNFIRED_PIZZA_BASE.get()), new ItemStack(FIRED_PIZZA_BASE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(new ItemStack(FIRED_PIZZA_BASE.get()), new ItemStack(UNBAKED_BREAD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(Items.BREAD), new ItemStack(BREAD_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(BREAD_SLICE.get()), new ItemStack(TOAST.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(TOAST.get()), new ItemStack(MOLDY_BREAD_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(MOLDY_BREAD_SLICE.get()), new ItemStack(UNBAKED_CRACKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(UNBAKED_CRACKER.get()), new ItemStack(CRACKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(new ItemStack(Items.PORKCHOP), new ItemStack(HAM_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(HAM_SLICE.get()), new ItemStack(RAW_BACON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(RAW_BACON.get()), new ItemStack(GREEN_HAM_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(Items.COOKED_PORKCHOP), new ItemStack(COOKED_HAM_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(COOKED_HAM_SLICE.get()), new ItemStack(COOKED_BACON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.accept(CHEESE_SLICE);
			event.accept(BLUE_CHEESE_SLICE);
			event.accept(GOUDA_CHEESE_SLICE);
			event.accept(SWISS_CHEESE_SLICE);
			event.accept(SWISS_CHEESE_BITES);
			event.accept(GOAT_CHEESE_SLICE);

			event.accept(SALT);
			event.accept(FLOUR);

			event.accept(CRACKED_EGG);
			event.accept(FRIED_EGG);
			event.accept(GREEN_CRACKED_EGG);

			event.accept(PINEAPPLE);
			event.accept(PINEAPPLE_RING);
			event.accept(PINEAPPLE_BITES);

			event.accept(TOMATO);
			event.accept(TOMATO_SLICE);
			event.accept(TOMATO_SAUCE);

			event.accept(CORN_KERNELS);
			event.accept(DRIED_CORN_KERNELS);

			event.accept(POPCORN_BAG);
			event.accept(POPCORN);
			event.accept(CHEESY_POPCORN);
			event.accept(CARAMEL_POPCORN);
			event.accept(MAPLE_POPCORN);

			event.accept(MOUSE);
			event.accept(COOKED_MOUSE);

			event.accept(FOOD_SCRAPS);

			event.accept(MAPLE_SAP_BOTTLE);
			event.accept(MAPLE_SYRUP);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
		{
			LOGGER.debug("Adding to Ingredients tab");
			event.accept(GEAR_WOODEN);
			event.accept(GEAR_STONE);
		}
	}
}
