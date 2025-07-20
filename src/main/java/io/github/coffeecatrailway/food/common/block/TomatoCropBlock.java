package io.github.coffeecatrailway.food.common.block;

import com.mojang.serialization.MapCodec;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class TomatoCropBlock extends DoubleCropBlock
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final int MAX_AGE = 9;
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

	public static final VoxelShape SHAPE = Shapes.block();

	public TomatoCropBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, 0));
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	protected VoxelShape[] getUpperShapes()
	{
		return new VoxelShape[0];
	}

	@Override
	protected VoxelShape[] getLowerShapes()
	{
		return new VoxelShape[0];
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
		return ModItems.TOMATO_SEEDS;
	}

	@Override
	public MapCodec<? extends CropBlock> codec()
	{
		return ModBlocks.TOMATO_CROP_CODEC.get();
	}
}
