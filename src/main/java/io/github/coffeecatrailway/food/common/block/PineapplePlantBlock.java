package io.github.coffeecatrailway.food.common.block;

import com.google.common.collect.Iterables;
import com.mojang.serialization.MapCodec;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.Iterator;

/**
 * @author CoffeeCatRailway
 * Created: 11/07/2025
 */
public class PineapplePlantBlock extends DoubleCropBlock
{
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);

	private static final VoxelShape[] SHAPES_TOP = new VoxelShape[]{
			Block.box(4d, 0d, 4d, 12d, 7d, 12d),
			Block.box(4d, 0d, 4d, 12d, 11d, 12d),
			Block.box(4d, 0d, 4d, 12d, 13d, 12d),
			Block.box(4d, 0d, 4d, 12d, 14d, 12d),
			Block.box(3d, 0d, 3d, 13d, 16d, 13d)
	};
	private static final VoxelShape[] SHAPES_BOTTOM = new VoxelShape[]{
			Block.box(6.5d, 0d, 6.5d, 9.5d, 6d, 9.5d),
			Block.box(6.5d, 0d, 6.5d, 9.5d, 8d, 9.5d),
			Block.box(6.5d, 0d, 6.5d, 9.5d, 12d, 9.5d),
			Block.box(6.5d, 0d, 6.5d, 9.5d, 14d, 9.5d),
			Block.box(5.5d, 0d, 5.5d, 10.5d, 16d, 10.5d)
	};

	public PineapplePlantBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public IntegerProperty getAgeProperty()
	{
		return AGE;
	}

	@Override
	public int getMaxAge()
	{
		// Iterables.getLast defaults to a while loop if not of type List
		int last = 0;
		for (Integer integer : AGE.getPossibleValues())
			last = integer;
		return last;
//		return 4;
	}

	@Override
	protected VoxelShape[] getTopShapes()
	{
		return SHAPES_TOP;
	}

	@Override
	protected VoxelShape[] getBottomShapes()
	{
		return SHAPES_BOTTOM;
	}

	@Override
	protected boolean placeableOn(BlockState state, BlockGetter level, BlockPos pos)
	{
		return (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL)) && !state.is(Blocks.FARMLAND);
	}

	@Override
	protected boolean needsFertileLand()
	{
		return false;
	}

	@Override
	protected Item getPickBlock(BlockState state)
	{
		return state.getValue(HALF) == DoubleBlockHalf.UPPER && !this.isYoung(state) ? ModItems.PINEAPPLE.get() : ModItems.PINEAPPLE_PLANT.get();
	}

	@Override
	protected void applyBonemeal(int age, ServerLevel level, BlockPos pos, BlockState state)
	{
		level.setBlock(pos, this.getStateForAge(age, state.getValue(HALF)), 2);
	}

	private void grow(BlockState state, BlockState newState, ServerLevel level, BlockPos pos, RandomSource random)
	{
//		float growthSpeed = this.getGrowthSpeed(state, level, pos);
//		if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int) (25f / growthSpeed) + 1) == 0))
		if (CommonHooks.canCropGrow(level, pos, state, random.nextInt(5) == 0)) // Same as Sweet Berry Bush
		{
			level.setBlock(pos, newState, 2);
			CommonHooks.fireCropGrowPost(level, pos, state);
		}
	}

	@Override
	public boolean isRandomlyTicking(BlockState state)
	{
		return state.getValue(HALF) == DoubleBlockHalf.LOWER || isYoung(state);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
	{
		if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (level.getRawBrightness(pos, 0) >= 9)
		{
			DoubleBlockHalf half = state.getValue(HALF);
			if (this.isYoung(state))
				this.grow(state, this.getStateForAge(state.getValue(AGE) + 1, half), level, pos, random);
			else
			{
				if (half == DoubleBlockHalf.LOWER && level.isEmptyBlock(pos.above()))
					this.grow(state, this.getStateForAge(0, DoubleBlockHalf.UPPER), level, pos.above(), random);
			}
		}
	}

	@Override
	protected MapCodec<? extends BushBlock> codec()
	{
		return ModBlocks.PINEAPPLE_PLANT_CODEC.get();
	}
}
