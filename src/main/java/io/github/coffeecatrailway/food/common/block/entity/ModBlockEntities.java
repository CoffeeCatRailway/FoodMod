package io.github.coffeecatrailway.food.common.block.entity;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.ChoppingBoardBlock;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 22/07/2025
 */
public class ModBlockEntities
{
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FoodMod.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChoppingBoardBlockEntity>> CHOPPING_BOARD = TYPES.register("chopping_board", () -> BlockEntityType.Builder.of(ChoppingBoardBlockEntity::new, ModBlocks.CHOPPING_BOARDS.get().toArray(new ChoppingBoardBlock[0])).build(null));

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering block entities");
		TYPES.register(modEventBus);
	}
}
