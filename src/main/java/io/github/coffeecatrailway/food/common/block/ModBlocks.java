package io.github.coffeecatrailway.food.common.block;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.datagen.LanguageGenerator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

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

	public static final DeferredBlock<PineappleCropBlock> PINEAPPLE_CROP = register("pineapple_plant", properties -> new PineappleCropBlock(properties
			.mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)), ID_NAME);
	public static final Supplier<MapCodec<PineappleCropBlock>> PINEAPPLE_CROP_CODEC = CODECS.register("pineapple_plant", () -> BlockBehaviour.simpleCodec(PineappleCropBlock::new));

	public static final DeferredBlock<TomatoCropBlock> TOMATO_CROP = register("tomato_plant", properties -> new TomatoCropBlock(properties
			.mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)), ID_NAME);
	public static final Supplier<MapCodec<TomatoCropBlock>> TOMATO_CROP_CODEC = CODECS.register("tomato_plant", () -> BlockBehaviour.simpleCodec(TomatoCropBlock::new));

	public static final DeferredBlock<CornCropBlock> CORN_CROP = register("corn_plant", properties -> new CornCropBlock(properties
			.mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)), ID_NAME);
	public static final Supplier<MapCodec<CornCropBlock>> CORN_CROP_CODEC = CODECS.register("corn_plant", () -> BlockBehaviour.simpleCodec(CornCropBlock::new));

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
