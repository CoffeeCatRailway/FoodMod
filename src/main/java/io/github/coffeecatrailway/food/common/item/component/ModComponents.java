package io.github.coffeecatrailway.food.common.item.component;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 28/05/2025
 */
public class ModComponents
{
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, FoodMod.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<FoodComboComponent>> FOOD_COMBO = COMPONENTS.registerComponentType("food_combo", builder -> builder.persistent(FoodComboComponent.CODEC).networkSynchronized(FoodComboComponent.STREAM_CODEC));

	public static void init(IEventBus modEventBus)
	{
		LOGGER.info("Registering item components");
		COMPONENTS.register(modEventBus);
	}
}
