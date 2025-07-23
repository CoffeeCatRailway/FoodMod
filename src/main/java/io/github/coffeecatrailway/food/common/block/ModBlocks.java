package io.github.coffeecatrailway.food.common.block;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.datagen.LanguageGenerator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author CoffeeCatRailway
 * Created: 11/07/2025
 */
public class ModBlocks
{
	private static final String ID_NAME = "ID";

	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FoodMod.MODID);
	public static final DeferredRegister<MapCodec<? extends Block>> CODECS = DeferredRegister.create(BuiltInRegistries.BLOCK_TYPE, FoodMod.MODID);

	public static final Supplier<MapCodec<PineappleCropBlock>> PINEAPPLE_CROP_CODEC = CODECS.register("pineapple_plant", () -> BlockBehaviour.simpleCodec(PineappleCropBlock::new));
	public static final DeferredBlock<PineappleCropBlock> PINEAPPLE_CROP = register("pineapple_plant", properties -> new PineappleCropBlock(properties
			.mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)), ID_NAME);

	public static final Supplier<MapCodec<TomatoCropBlock>> TOMATO_CROP_CODEC = CODECS.register("tomato_plant", () -> BlockBehaviour.simpleCodec(TomatoCropBlock::new));
	public static final DeferredBlock<TomatoCropBlock> TOMATO_CROP = register("tomato_plant", properties -> new TomatoCropBlock(properties
			.mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)), ID_NAME);

	public static final Supplier<MapCodec<CornCropBlock>> CORN_CROP_CODEC = CODECS.register("corn_plant", () -> BlockBehaviour.simpleCodec(CornCropBlock::new));
	public static final DeferredBlock<CornCropBlock> CORN_CROP = register("corn_plant", properties -> new CornCropBlock(properties
			.mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)), ID_NAME);

	public static final Supplier<MapCodec<ChoppingBoardBlock>> CHOPPING_BOARD_CODEC = CODECS.register("chopping_board", () -> BlockBehaviour.simpleCodec(ChoppingBoardBlock::new));
	public static final DeferredBlock<ChoppingBoardBlock> OAK_CHOPPING_BOARD = registerChoppingBoard("oak_chopping_board", () -> Blocks.OAK_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> BIRCH_CHOPPING_BOARD = registerChoppingBoard("birch_chopping_board", () -> Blocks.BIRCH_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> SPRUCE_CHOPPING_BOARD = registerChoppingBoard("spruce_chopping_board", () -> Blocks.SPRUCE_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> JUNGLE_CHOPPING_BOARD = registerChoppingBoard("jungle_chopping_board", () -> Blocks.JUNGLE_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> ACACIA_CHOPPING_BOARD = registerChoppingBoard("acacia_chopping_board", () -> Blocks.ACACIA_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> DARK_OAK_CHOPPING_BOARD = registerChoppingBoard("dark_oak_chopping_board", () -> Blocks.DARK_OAK_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> CRIMSON_CHOPPING_BOARD = registerChoppingBoard("crimson_chopping_board", () -> Blocks.CRIMSON_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> WARPED_CHOPPING_BOARD = registerChoppingBoard("warped_chopping_board", () -> Blocks.WARPED_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> CHERRY_CHOPPING_BOARD = registerChoppingBoard("cherry_chopping_board", () -> Blocks.CHERRY_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> MANGROVE_CHOPPING_BOARD = registerChoppingBoard("mangrove_chopping_board", () -> Blocks.MANGROVE_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> BAMBOO_CHOPPING_BOARD = registerChoppingBoard("bamboo_chopping_board", () -> Blocks.BAMBOO_PRESSURE_PLATE);

	public static final DeferredBlock<ChoppingBoardBlock> STONE_CHOPPING_BOARD = registerChoppingBoard("stone_chopping_board", () -> Blocks.STONE_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> POLISHED_BLACKSTONE_CHOPPING_BOARD = registerChoppingBoard("polished_blackstone_chopping_board", () -> Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> GOLD_CHOPPING_BOARD = registerChoppingBoard("gold_chopping_board", () -> Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
	public static final DeferredBlock<ChoppingBoardBlock> IRON_CHOPPING_BOARD = registerChoppingBoard("iron_chopping_board", () -> Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);

//	public static final DeferredBlock<ChoppingBoardBlock> MAPLE_CHOPPING_BOARD = registerChoppingBoard("maple_chopping_board", () -> ModBlocks.MAPLE_PRESSURE_PLATE);

	public static final Supplier<Set<ChoppingBoardBlock>> CHOPPING_BOARDS = () -> BuiltInRegistries.BLOCK.stream().filter(block -> block instanceof ChoppingBoardBlock).map(block -> (ChoppingBoardBlock) block).collect(Collectors.toSet());

	private static <T extends Block> DeferredBlock<ChoppingBoardBlock> registerChoppingBoard(String id, Supplier<T> base)
	{
		return registerWithItem(id, properties -> new ChoppingBoardBlock(BlockBehaviour.Properties.ofFullCopy(base.get()).strength(.5f)), ID_NAME);
	}

	private static <T extends Block> DeferredBlock<T> registerWithItem(String id, Function<BlockBehaviour.Properties, T> factory, String name)
	{
		DeferredBlock<T> block = register(id, factory, name);
		ModItems.ITEMS.registerSimpleBlockItem(block);
		return block;
	}

	private static <T extends Block> DeferredBlock<T> register(String id, Function<BlockBehaviour.Properties, T> factory, String name)
	{
		DeferredBlock<T> block = BLOCKS.registerBlock(id, factory);
		LanguageGenerator.BLOCKS.put(block, name.equals(ID_NAME) ? LanguageGenerator.capitalize(id) : name);
		return block;
	}

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering blocks");
		BLOCKS.register(modEventBus);
	}
}
