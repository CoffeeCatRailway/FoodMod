package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.CornCropBlock;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.common.block.PineappleCropBlock;
import io.github.coffeecatrailway.food.common.block.TomatoCropBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
	}
}
