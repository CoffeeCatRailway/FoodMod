package io.github.coffeecatrailway.food.common.block;

import com.mojang.serialization.MapCodec;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * @author CoffeeCatRailway
 * Created: 11/07/2025
 */
public class PineapplePlantBlock extends CropBlock
{
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);

	private static final VoxelShape[] SHAPES = new VoxelShape[]{
			Block.box(5d, 0d, 5d, 11d, 7d, 11d),
			Block.box(4d, 0d, 4d, 12d, 11d, 12d),
			Block.box(4d, 0d, 4d, 12d, 13d, 12d),
			Block.box(4d, 0d, 4d, 12d, 14d, 12d),
			Block.box(3d, 0d, 3d, 13d, 16d, 13d)
	};

	public PineapplePlantBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return SHAPES[this.getAge(state)];
	}

	@Override
	protected IntegerProperty getAgeProperty()
	{
		return AGE;
	}

	@Override
	public int getMaxAge()
	{
		return 4;
	}

	@Override
	protected ItemLike getBaseSeedId()
	{
		return ModItems.PINEAPPLE_CROWN;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(AGE);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		return super.canSurvive(state, level, pos) && level.getBlockState(pos.above()).isAir();
	}

	@Override
	public MapCodec<? extends CropBlock> codec()
	{
		return ModBlocks.PINEAPPLE_PLANT_CODEC.get();
	}
}
