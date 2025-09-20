package io.github.coffeecatrailway.food.common;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2025
 */
public class ModStats
{
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT, FoodMod.MODID);

	public static final ResourceLocation INTERACT_PIZZA_OVEN = register("interact_pizza_oven", StatFormatter.DEFAULT);
	public static final ResourceLocation INTERACT_GRILL = register("interact_grill", StatFormatter.DEFAULT);
	public static final ResourceLocation INTERACT_POPCORN_MACHINE = register("interact_popcorn_machine", StatFormatter.DEFAULT);

	public static final ResourceLocation INTERACT_CHOPPING_BOARD = register("interact_chopping_board", StatFormatter.DEFAULT);

	private static ResourceLocation register(String id, StatFormatter formatter)
	{
		ResourceLocation stat = FoodMod.id(id);
		STATS.register(id, () -> stat);
//		Stats.CUSTOM.get(stat, formatter);
		return stat;
	}

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering criteria triggers");
		STATS.register(modEventBus);
	}
}
