package io.github.coffeecatrailway.food.common.advancements;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2025
 */
public class ModCriteriaTriggers
{
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(BuiltInRegistries.TRIGGER_TYPES, FoodMod.MODID);

	public static final DeferredHolder<CriterionTrigger<?>, ChoppingBoardTrigger> CHOPPING_BOARD = TRIGGERS.register("use_chopping_board", ChoppingBoardTrigger::new);

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering criteria triggers");
		TRIGGERS.register(modEventBus);
	}
}
