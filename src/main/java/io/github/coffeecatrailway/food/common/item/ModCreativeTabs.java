package io.github.coffeecatrailway.food.common.item;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 21/07/2025
 */
public class ModCreativeTabs
{
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, FoodMod.MODID);

	public static final Supplier<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("example", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup." + FoodMod.MODID + ".tab"))
			.icon(() -> new ItemStack(ModItems.CORN_COB.get()))
			.displayItems((params, output) -> {
				// Blocks
				output.accept(ModBlocks.OAK_CHOPPING_BOARD);
				output.accept(ModBlocks.BIRCH_CHOPPING_BOARD);
				output.accept(ModBlocks.SPRUCE_CHOPPING_BOARD);
				output.accept(ModBlocks.JUNGLE_CHOPPING_BOARD);
				output.accept(ModBlocks.ACACIA_CHOPPING_BOARD);
				output.accept(ModBlocks.DARK_OAK_CHOPPING_BOARD);
				output.accept(ModBlocks.CRIMSON_CHOPPING_BOARD);
				output.accept(ModBlocks.WARPED_CHOPPING_BOARD);
				output.accept(ModBlocks.CHERRY_CHOPPING_BOARD);
				output.accept(ModBlocks.MANGROVE_CHOPPING_BOARD);
				output.accept(ModBlocks.BAMBOO_CHOPPING_BOARD);

				output.accept(ModBlocks.STONE_CHOPPING_BOARD);
				output.accept(ModBlocks.POLISHED_BLACKSTONE_CHOPPING_BOARD);
				output.accept(ModBlocks.GOLD_CHOPPING_BOARD);
				output.accept(ModBlocks.IRON_CHOPPING_BOARD);

//				output.accept(ModBlocks.MAPLE_CHOPPING_BOARD);

				// General Items
				output.accept(ModItems.GEAR_WOODEN);
				output.accept(ModItems.GEAR_STONE);

				// Tools
				output.accept(ModItems.CURDLER_WOODEN);
				output.accept(ModItems.CURDLER_STONE);

				output.accept(ModItems.ROLLING_PIN_WOODEN);
				output.accept(ModItems.ROLLING_PIN_STONE);

				output.accept(ModItems.GRIND_STONES);

				output.accept(ModItems.KNIFE_WOODEN);
				output.accept(ModItems.KNIFE_STONE);
				output.accept(ModItems.KNIFE_COPPER);
				output.accept(ModItems.KNIFE_GOLD);
				output.accept(ModItems.KNIFE_IRON);
				output.accept(ModItems.KNIFE_DIAMOND);
				output.accept(ModItems.KNIFE_NETHERITE);

				// Foods
				output.accept(ModItems.CHEESE_SLICE);
				output.accept(ModItems.BLUE_CHEESE_SLICE);
				output.accept(ModItems.GOUDA_CHEESE_SLICE);
				output.accept(ModItems.SWISS_CHEESE_SLICE);
				output.accept(ModItems.SWISS_CHEESE_BITES);
				output.accept(ModItems.GOAT_CHEESE_SLICE);

				output.accept(ModItems.SALT);
				output.accept(ModItems.FLOUR);

				output.accept(ModItems.DOUGH);

				output.accept(ModItems.UNFIRED_PIZZA_BASE);
				output.accept(ModItems.FIRED_PIZZA_BASE);
//				output.accept(ModItems.PIZZA);

				output.accept(ModItems.UNBAKED_BREAD);
				output.accept(ModItems.BREAD_SLICE);
				output.accept(ModItems.TOAST);
				output.accept(ModItems.MOLDY_BREAD_SLICE);

				output.accept(ModItems.UNBAKED_CRACKER);
				output.accept(ModItems.CRACKER);

				output.accept(ModItems.CRACKED_EGG);
				output.accept(ModItems.FRIED_EGG);
				output.accept(ModItems.GREEN_CRACKED_EGG);

				output.accept(ModItems.HAM_SLICE);
				output.accept(ModItems.COOKED_HAM_SLICE);
				output.accept(ModItems.GREEN_HAM_SLICE);

				output.accept(ModItems.RAW_BACON);
				output.accept(ModItems.COOKED_BACON);

//				output.accept(ModItems.SANDWICH);

				output.accept(ModItems.PINEAPPLE_CROWN);
				output.accept(ModItems.PINEAPPLE);
				output.accept(ModItems.PINEAPPLE_RING);
				output.accept(ModItems.PINEAPPLE_BITES);

				output.accept(ModItems.TOMATO_SEEDS);
				output.accept(ModItems.TOMATO);
				output.accept(ModItems.TOMATO_SLICE);
				output.accept(ModItems.TOMATO_SAUCE);

				output.accept(ModItems.CORN_COB);
				output.accept(ModItems.CORN_KERNELS);
				output.accept(ModItems.DRIED_CORN_KERNELS);

				output.accept(ModItems.POPCORN_BAG);
				output.accept(ModItems.POPCORN);
				output.accept(ModItems.CHEESY_POPCORN);
				output.accept(ModItems.CARAMEL_POPCORN);
				output.accept(ModItems.MAPLE_POPCORN);

				output.accept(ModItems.MOUSE);
				output.accept(ModItems.COOKED_MOUSE);

				output.accept(ModItems.FOOD_SCRAPS);

				output.accept(ModItems.MAPLE_SAP_BOTTLE);
				output.accept(ModItems.MAPLE_SYRUP);
			})
			.build()
	);

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering items");
		CREATIVE_MODE_TABS.register(modEventBus);

		modEventBus.addListener(ModCreativeTabs::addCreative);
	}

	private static void addCreative(BuildCreativeModeTabContentsEvent event)
	{
		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
		{
			LOGGER.debug("Adding to Natural Blocks tab");
			event.insertAfter(new ItemStack(Items.NETHER_WART), new ItemStack(ModItems.PINEAPPLE_CROWN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.PINEAPPLE_CROWN.get()), new ItemStack(ModItems.TOMATO_SEEDS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.TOMATO_SEEDS.get()), new ItemStack(ModItems.CORN_COB.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}

		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
		{
			LOGGER.debug("Adding to Functional Blocks tab");
			event.accept(ModBlocks.OAK_CHOPPING_BOARD);
			event.accept(ModBlocks.BIRCH_CHOPPING_BOARD);
			event.accept(ModBlocks.SPRUCE_CHOPPING_BOARD);
			event.accept(ModBlocks.JUNGLE_CHOPPING_BOARD);
			event.accept(ModBlocks.ACACIA_CHOPPING_BOARD);
			event.accept(ModBlocks.DARK_OAK_CHOPPING_BOARD);
			event.accept(ModBlocks.CRIMSON_CHOPPING_BOARD);
			event.accept(ModBlocks.WARPED_CHOPPING_BOARD);
			event.accept(ModBlocks.CHERRY_CHOPPING_BOARD);
			event.accept(ModBlocks.MANGROVE_CHOPPING_BOARD);
			event.accept(ModBlocks.BAMBOO_CHOPPING_BOARD);

			event.accept(ModBlocks.STONE_CHOPPING_BOARD);
			event.accept(ModBlocks.POLISHED_BLACKSTONE_CHOPPING_BOARD);
			event.accept(ModBlocks.GOLD_CHOPPING_BOARD);
			event.accept(ModBlocks.IRON_CHOPPING_BOARD);

//			event.accept(ModBlocks.MAPLE_CHOPPING_BOARD);
		}

		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
		{
			LOGGER.debug("Adding to Tools And Utilities tab");
			final ItemStack WOODEN_HOE = new ItemStack(Items.WOODEN_HOE);
			final ItemStack STONE_HOE = new ItemStack(Items.STONE_HOE);
			final ItemStack GOLDEN_HOE = new ItemStack(Items.GOLDEN_HOE);
			final ItemStack IRON_HOE = new ItemStack(Items.IRON_HOE);
			final ItemStack DIAMOND_HOE = new ItemStack(Items.DIAMOND_HOE);
			final ItemStack NETHERITE_HOE = new ItemStack(Items.NETHERITE_HOE);

			event.insertAfter(WOODEN_HOE, new ItemStack(ModItems.CURDLER_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(ModItems.CURDLER_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(WOODEN_HOE, new ItemStack(ModItems.ROLLING_PIN_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(ModItems.ROLLING_PIN_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(WOODEN_HOE, new ItemStack(ModItems.KNIFE_WOODEN.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(STONE_HOE, new ItemStack(ModItems.KNIFE_STONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(GOLDEN_HOE, new ItemStack(ModItems.KNIFE_GOLD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(IRON_HOE, new ItemStack(ModItems.KNIFE_IRON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(DIAMOND_HOE, new ItemStack(ModItems.KNIFE_DIAMOND.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(NETHERITE_HOE, new ItemStack(ModItems.KNIFE_NETHERITE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.accept(ModItems.GRIND_STONES);
			event.accept(ModItems.KNIFE_COPPER);
		}

		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS)
		{
			LOGGER.debug("Adding to Food And Drinks tab");
			event.insertBefore(new ItemStack(Items.BREAD), new ItemStack(ModItems.DOUGH.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(new ItemStack(ModItems.DOUGH.get()), new ItemStack(ModItems.UNFIRED_PIZZA_BASE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.UNFIRED_PIZZA_BASE.get()), new ItemStack(ModItems.FIRED_PIZZA_BASE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(new ItemStack(ModItems.FIRED_PIZZA_BASE.get()), new ItemStack(ModItems.UNBAKED_BREAD.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(Items.BREAD), new ItemStack(ModItems.BREAD_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.BREAD_SLICE.get()), new ItemStack(ModItems.TOAST.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.TOAST.get()), new ItemStack(ModItems.MOLDY_BREAD_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.MOLDY_BREAD_SLICE.get()), new ItemStack(ModItems.UNBAKED_CRACKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.UNBAKED_CRACKER.get()), new ItemStack(ModItems.CRACKER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.insertAfter(new ItemStack(Items.PORKCHOP), new ItemStack(ModItems.HAM_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.HAM_SLICE.get()), new ItemStack(ModItems.RAW_BACON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.RAW_BACON.get()), new ItemStack(ModItems.GREEN_HAM_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(Items.COOKED_PORKCHOP), new ItemStack(ModItems.COOKED_HAM_SLICE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
			event.insertAfter(new ItemStack(ModItems.COOKED_HAM_SLICE.get()), new ItemStack(ModItems.COOKED_BACON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

			event.accept(ModItems.CHEESE_SLICE);
			event.accept(ModItems.BLUE_CHEESE_SLICE);
			event.accept(ModItems.GOUDA_CHEESE_SLICE);
			event.accept(ModItems.SWISS_CHEESE_SLICE);
			event.accept(ModItems.SWISS_CHEESE_BITES);
			event.accept(ModItems.GOAT_CHEESE_SLICE);

			event.accept(ModItems.SALT);
			event.accept(ModItems.FLOUR);

			event.accept(ModItems.CRACKED_EGG);
			event.accept(ModItems.FRIED_EGG);
			event.accept(ModItems.GREEN_CRACKED_EGG);

			event.accept(ModItems.PINEAPPLE);
			event.accept(ModItems.PINEAPPLE_RING);
			event.accept(ModItems.PINEAPPLE_BITES);

			event.accept(ModItems.TOMATO);
			event.accept(ModItems.TOMATO_SLICE);
			event.accept(ModItems.TOMATO_SAUCE);

			event.accept(ModItems.CORN_KERNELS);
			event.accept(ModItems.DRIED_CORN_KERNELS);

			event.accept(ModItems.POPCORN_BAG);
			event.accept(ModItems.POPCORN);
			event.accept(ModItems.CHEESY_POPCORN);
			event.accept(ModItems.CARAMEL_POPCORN);
			event.accept(ModItems.MAPLE_POPCORN);

			event.accept(ModItems.MOUSE);
			event.accept(ModItems.COOKED_MOUSE);

			event.accept(ModItems.FOOD_SCRAPS);

			event.accept(ModItems.MAPLE_SAP_BOTTLE);
			event.accept(ModItems.MAPLE_SYRUP);
		}

		if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
		{
			LOGGER.debug("Adding to Ingredients tab");
			event.accept(ModItems.GEAR_WOODEN);
			event.accept(ModItems.GEAR_STONE);
		}
	}
}
