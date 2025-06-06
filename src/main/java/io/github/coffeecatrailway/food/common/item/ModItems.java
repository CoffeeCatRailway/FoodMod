package io.github.coffeecatrailway.food.common.item;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
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

	public static final int SLICE_PER_BREAD = 4;
	public static final FoodProperties FOOD_BREAD_SLICE = new FoodProperties.Builder().alwaysEdible().nutrition(Foods.BREAD.nutrition() / SLICE_PER_BREAD).saturationModifier(Foods.BREAD.saturation() / (float) SLICE_PER_BREAD).build();
	public static final FoodProperties FOOD_TOAST = new FoodProperties.Builder().alwaysEdible().nutrition((int) (FOOD_BREAD_SLICE.nutrition() * 1.5f)).saturationModifier(FOOD_BREAD_SLICE.saturation() * 1.5f).build();

	public static final DeferredItem<Item> BREAD_SLICE = ITEMS.registerSimpleItem("bread_slice", new Item.Properties().food(FOOD_BREAD_SLICE));
	public static final DeferredItem<Item> TOAST = ITEMS.registerSimpleItem("toast", new Item.Properties().food(FOOD_TOAST));

	public static final DeferredItem<ItemCraftingTool> KNIFE_WOODEN = ITEMS.registerItem("knife_wooden", prop -> itemCraftingTool(prop, Tiers.WOOD, 1.f, -2.5f));
	public static final DeferredItem<ItemCraftingTool> KNIFE_STONE = ITEMS.registerItem("knife_stone", prop -> itemCraftingTool(prop, Tiers.STONE, 1.f, -2.5f));
	public static final DeferredItem<ItemCraftingTool> KNIFE_COPPER = ITEMS.registerItem("knife_copper", prop -> itemCraftingTool(prop, Tiers.STONE, 1.f, -2.5f));
	public static final DeferredItem<ItemCraftingTool> KNIFE_GOLD = ITEMS.registerItem("knife_gold", prop -> itemCraftingTool(prop, Tiers.GOLD, 1.f, -2.5f));
	public static final DeferredItem<ItemCraftingTool> KNIFE_IRON = ITEMS.registerItem("knife_iron", prop -> itemCraftingTool(prop, Tiers.IRON, 1.f, -2.5f));
	public static final DeferredItem<ItemCraftingTool> KNIFE_DIAMOND = ITEMS.registerItem("knife_diamond", prop -> itemCraftingTool(prop, Tiers.DIAMOND, 1.f, -2.5f));
	public static final DeferredItem<ItemCraftingTool> KNIFE_NETHERITE = ITEMS.registerItem("knife_netherite", prop -> itemCraftingTool(prop, Tiers.NETHERITE, 1.f, -2.5f));

	private static ItemCraftingTool itemCraftingTool(Item.Properties prop, Tier tier, float attackDamage, float attackSpeed)
	{
		return new ItemCraftingTool(tier, prop.durability(tier.getUses() / 2).attributes(ItemCraftingTool.createAttributes(tier, attackDamage, attackSpeed)));
	}

	public static void init(IEventBus modEventBus)
	{
		LOGGER.info("Registering items");
		ModItems.ITEMS.register(modEventBus);

		modEventBus.addListener(ModItems::addCreative);
	}

	private static void addCreative(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
		{
			LOGGER.info("Adding food items to tabs");
			event.accept(BREAD_SLICE);
			event.accept(TOAST);
		} else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
		{
			LOGGER.info("Adding tool items to tabs");
			event.accept(KNIFE_WOODEN);
			event.accept(KNIFE_STONE);
			event.accept(KNIFE_COPPER);
			event.accept(KNIFE_GOLD);
			event.accept(KNIFE_IRON);
			event.accept(KNIFE_DIAMOND);
			event.accept(KNIFE_NETHERITE);
		}
	}
}
