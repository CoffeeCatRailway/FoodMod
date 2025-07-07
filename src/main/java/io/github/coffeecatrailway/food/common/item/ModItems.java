package io.github.coffeecatrailway.food.common.item;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.ModFoods;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 27/05/2025
 */
public class ModItems
{
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FoodMod.MODID);

	// Crafting Materials
	public static final DeferredItem<Item> GEAR_WOODEN = ITEMS.registerSimpleItem("gear_wooden");
	public static final DeferredItem<Item> GEAR_STONE = ITEMS.registerSimpleItem("gear_stone");

	// Tools
	public static final DeferredItem<CraftingToolItem> CURDLER_WOODEN = ITEMS.registerItem("curdler_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 2f, -2.5f));
	public static final DeferredItem<CraftingToolItem> CURDLER_STONE = ITEMS.registerItem("curdler_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 2f, -2.5f));

	public static final DeferredItem<CraftingToolItem> ROLLING_PIN_WOODEN = ITEMS.registerItem("rolling_pin_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> ROLLING_PIN_STONE = ITEMS.registerItem("rolling_pin_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 1f, -2.5f));

	public static final DeferredItem<CraftingToolItem> GRIND_STONES = ITEMS.registerItem("grind_stones", prop -> itemCraftingTool(prop, Tiers.STONE, 2f, -2.0f));

	public static final DeferredItem<CraftingToolItem> KNIFE_WOODEN = ITEMS.registerItem("knife_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> KNIFE_STONE = ITEMS.registerItem("knife_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> KNIFE_COPPER = ITEMS.registerItem("knife_copper", prop -> itemCraftingTool(prop, Tiers.STONE, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> KNIFE_GOLD = ITEMS.registerItem("knife_gold", prop -> itemCraftingTool(prop, Tiers.GOLD, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> KNIFE_IRON = ITEMS.registerItem("knife_iron", prop -> itemCraftingTool(prop, Tiers.IRON, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> KNIFE_DIAMOND = ITEMS.registerItem("knife_diamond", prop -> itemCraftingTool(prop, Tiers.DIAMOND, 1f, -2.5f));
	public static final DeferredItem<CraftingToolItem> KNIFE_NETHERITE = ITEMS.registerItem("knife_netherite", prop -> itemCraftingTool(prop, Tiers.NETHERITE, 1f, -2.5f));

	// Foods
	public static final DeferredItem<Item> BREAD_SLICE = ITEMS.registerSimpleItem("bread_slice", new Item.Properties().food(ModFoods.FOOD_BREAD_SLICE));
	public static final DeferredItem<Item> TOAST = ITEMS.registerSimpleItem("toast", new Item.Properties().food(ModFoods.FOOD_TOAST));

	public static final DeferredItem<SandwichItem> SANDWICH = ITEMS.registerItem("sandwich", SandwichItem::new);

	private static CraftingToolItem itemCraftingTool(Item.Properties prop, Tier tier, float attackDamage, float attackSpeed)
	{
		return new CraftingToolItem(tier, prop.durability(tier.getUses() / 2).attributes(CraftingToolItem.createAttributes(tier, attackDamage, attackSpeed)));
	}

	public static void init(IEventBus modEventBus)
	{
		LOGGER.info("Registering items");
		ModItems.ITEMS.register(modEventBus);

		modEventBus.addListener(ModItems::addCreative);
	}

	private static void addCreative(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
		{
			LOGGER.info("Adding tool items to tabs");
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

			event.accept(GRIND_STONES);

			event.insertAfter(WOODEN_HOE, new ItemStack(KNIFE_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(KNIFE_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(KNIFE_STONE.get()), new ItemStack(KNIFE_COPPER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(GOLDEN_HOE, new ItemStack(KNIFE_GOLD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(IRON_HOE, new ItemStack(KNIFE_IRON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(DIAMOND_HOE, new ItemStack(KNIFE_DIAMOND.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(NETHERITE_HOE, new ItemStack(KNIFE_NETHERITE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}

		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
		{
			LOGGER.info("Adding food items to tabs");
			event.insertAfter(new ItemStack(Items.BREAD), new ItemStack(BREAD_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(BREAD_SLICE.get()), new ItemStack(TOAST.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
		{
			LOGGER.info("Adding ingredient items to tabs");
			event.accept(GEAR_WOODEN);
			event.accept(GEAR_STONE);
		}
	}
}
