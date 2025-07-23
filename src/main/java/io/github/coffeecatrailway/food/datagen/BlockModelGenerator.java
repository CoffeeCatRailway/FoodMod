package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class BlockModelGenerator extends BlockStateProvider
{
	public BlockModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
	{
		super(output, FoodMod.MODID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels()
	{
		this.getVariantBuilder(ModBlocks.PINEAPPLE_CROP.get()).forAllStates(state -> {
			int age = state.getValue(PineappleCropBlock.AGE);

			String path = "pineapple_plant_stage_" + age;
			BlockModelBuilder model = this.models().withExistingParent(path, FoodMod.id("block/pineapple_plant")).texture("texture", FoodMod.id("block/" + path)).renderType(RenderType.CUTOUT.name);

			return ConfiguredModel.builder().modelFile(model).build();
		});

		this.getVariantBuilder(ModBlocks.TOMATO_CROP.get()).forAllStates(state -> {
			int age = state.getValue(TomatoCropBlock.AGE);
			DoubleBlockHalf half = state.getValue(TomatoCropBlock.HALF);

			String path = "tomato_plant_" + half.getSerializedName() + "_stage_" + age;
			BlockModelBuilder model = this.models().withExistingParent(path, ResourceLocation.withDefaultNamespace("block/crop")).texture("crop", FoodMod.id("block/" + path)).renderType(RenderType.CUTOUT.name);

			return ConfiguredModel.builder().modelFile(model).build();
		});

		this.getVariantBuilder(ModBlocks.CORN_CROP.get()).forAllStates(state -> {
			int age = state.getValue(CornCropBlock.AGE);
			DoubleBlockHalf half = state.getValue(CornCropBlock.HALF);

			String path = "corn_plant_" + half.getSerializedName() + "_stage_" + age;
			String texture = (age < 3 && half == DoubleBlockHalf.UPPER) ? "empty" : path;
			BlockModelBuilder model = this.models().withExistingParent(path, ResourceLocation.withDefaultNamespace("block/cross")).texture("cross", "block/" + texture).renderType(RenderType.CUTOUT.name);

			return ConfiguredModel.builder().modelFile(model).build();
		});

		this.choppingBoard(ModBlocks.OAK_CHOPPING_BOARD.get(), Blocks.OAK_PLANKS);
		this.choppingBoard(ModBlocks.BIRCH_CHOPPING_BOARD.get(), Blocks.BIRCH_PLANKS);
		this.choppingBoard(ModBlocks.SPRUCE_CHOPPING_BOARD.get(), Blocks.SPRUCE_PLANKS);
		this.choppingBoard(ModBlocks.JUNGLE_CHOPPING_BOARD.get(), Blocks.JUNGLE_PLANKS);
		this.choppingBoard(ModBlocks.ACACIA_CHOPPING_BOARD.get(), Blocks.ACACIA_PLANKS);
		this.choppingBoard(ModBlocks.DARK_OAK_CHOPPING_BOARD.get(), Blocks.DARK_OAK_PLANKS);
		this.choppingBoard(ModBlocks.CRIMSON_CHOPPING_BOARD.get(), Blocks.CRIMSON_PLANKS);
		this.choppingBoard(ModBlocks.WARPED_CHOPPING_BOARD.get(), Blocks.WARPED_PLANKS);
		this.choppingBoard(ModBlocks.CHERRY_CHOPPING_BOARD.get(), Blocks.CHERRY_PLANKS);
		this.choppingBoard(ModBlocks.MANGROVE_CHOPPING_BOARD.get(), Blocks.MANGROVE_PLANKS);
		this.choppingBoard(ModBlocks.BAMBOO_CHOPPING_BOARD.get(), Blocks.BAMBOO_PLANKS);

		this.choppingBoard(ModBlocks.STONE_CHOPPING_BOARD.get(), Blocks.STONE);
		this.choppingBoard(ModBlocks.POLISHED_BLACKSTONE_CHOPPING_BOARD.get(), Blocks.POLISHED_BLACKSTONE);
		this.choppingBoard(ModBlocks.GOLD_CHOPPING_BOARD.get(), Blocks.GOLD_BLOCK);
		this.choppingBoard(ModBlocks.IRON_CHOPPING_BOARD.get(), Blocks.IRON_BLOCK);

//		this.choppingBoard(ModBlocks.MAPLE_CHOPPING_BOARD.get(), ModBlocks.MAPLE_PLANKS);
	}

	private void choppingBoard(ChoppingBoardBlock boardBlock, Block baseBlock)
	{
		ResourceLocation texture = TextureMapping.getBlockTexture(baseBlock);

		this.getVariantBuilder(boardBlock).forAllStates(state -> {
			BlockModelBuilder model = this.models().withExistingParent(BuiltInRegistries.BLOCK.getKey(boardBlock).getPath(), FoodMod.id("block/chopping_board")).texture("texture", texture);
			int rotation = yRotationFromDirectioni(state.getValue(ChoppingBoardBlock.FACING));

			return ConfiguredModel.builder().modelFile(model).rotationY(rotation).build();
		});
		this.itemModels().simpleBlockItem(boardBlock);
	}

	private VariantProperties.Rotation yRotationFromDirection(Direction direction)
	{
		return switch (direction)
		{
			case SOUTH -> VariantProperties.Rotation.R180;
			case WEST -> VariantProperties.Rotation.R270;
			case EAST -> VariantProperties.Rotation.R90;
			default -> VariantProperties.Rotation.R0;
		};
	}

	private int yRotationFromDirectioni(Direction direction)
	{
		return switch (direction)
		{
			case SOUTH -> 180;
			case WEST -> 270;
			case EAST -> 90;
			default -> 0;
		};
	}
}
