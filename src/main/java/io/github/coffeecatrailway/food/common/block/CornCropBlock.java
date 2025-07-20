package io.github.coffeecatrailway.food.common.block;

import com.mojang.serialization.MapCodec;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author CoffeeCatRailway
 * Created: 20/07/2025
 */
public class CornCropBlock extends DoubleCropBlock
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final int MAX_AGE = 6;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

	private static final VoxelShape[] SHAPES_UPPER = new VoxelShape[]{
			Block.box(6d, -16d, 6d, 10d, -15d, 10d),
			Block.box(6d, -16d, 6d, 10d, -15d, 10d),
			Block.box(6d, -16d, 6d, 10d, -15d, 10d),
			Block.box(4d, 0d, 4d, 12d, 5d, 12d),
			Block.box(4d, 0d, 4d, 12d, 9d, 12d),
			Block.box(4d, 0d, 4d, 12d, 14d, 12d),
			Block.box(3d, 0d, 3d, 13d, 15d, 13d)
	};
	private static final VoxelShape[] SHAPES_LOWER = new VoxelShape[]{
			Block.box(6d, 0d, 6d, 10d, 7d, 10d),
			Block.box(4d, 0d, 4d, 12d, 11d, 12d),
			Block.box(3d, 0d, 3d, 12d, 16d, 12d),
			Block.box(3d, 0d, 3d, 13d, 16d, 13d),
			Block.box(3d, 0d, 3d, 13d, 16d, 13d),
			Block.box(3d, 0d, 3d, 13d, 16d, 13d),
			Block.box(3d, 0d, 3d, 13d, 16d, 13d)
	};

	public CornCropBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, 0));
	}

	@Override
	protected VoxelShape[] getUpperShapes()
	{
		return SHAPES_UPPER;
	}

	@Override
	protected VoxelShape[] getLowerShapes()
	{
		return SHAPES_LOWER;
	}

	@Override
	protected IntegerProperty getAgeProperty()
	{
		return AGE;
	}

	@Override
	public int getMaxAge()
	{
		return MAX_AGE;
	}

	@Override
	protected ItemLike getBaseSeedId()
	{
		return ModItems.CORN_COB;
	}

	@Override
	public MapCodec<? extends CropBlock> codec()
	{
		return ModBlocks.CORN_CROP_CODEC.get();
	}
}
