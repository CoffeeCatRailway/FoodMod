package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.common.block.PineapplePlantBlock;
import io.github.coffeecatrailway.food.common.block.TomatoPlantBlock;
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
		this.getVariantBuilder(ModBlocks.PINEAPPLE_PLANT.get()).forAllStates(state -> {
			int age = state.getValue(PineapplePlantBlock.AGE);

			String path = "pineapple_plant_stage_" + age;
			BlockModelBuilder model = this.models().withExistingParent(path, FoodMod.id("block/pineapple_plant")).texture("texture", FoodMod.id("block/" + path)).renderType(RenderType.CUTOUT.name);

			return ConfiguredModel.builder().modelFile(model).build();
		});

		this.getVariantBuilder(ModBlocks.TOMATO_PLANT.get()).forAllStates(state -> {
			int age = state.getValue(TomatoPlantBlock.AGE);
			DoubleBlockHalf half = state.getValue(TomatoPlantBlock.HALF);

			String path = "tomato_plant_" + half.getSerializedName() + "_stage_" + age;

			BlockModelBuilder model = this.models().withExistingParent(path, ResourceLocation.withDefaultNamespace("block/crop")).texture("crop", FoodMod.id("block/" + path)).renderType(RenderType.CUTOUT.name);

			return ConfiguredModel.builder().modelFile(model).build();
		});
	}
}
