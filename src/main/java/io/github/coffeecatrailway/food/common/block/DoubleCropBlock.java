package io.github.coffeecatrailway.food.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.Nullable;

/**
 * @author CoffeeCatRailway
 * Created: 20/07/2025
 */
public abstract class DoubleCropBlock extends CropBlock
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	public DoubleCropBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(this.getAgeProperty(), 0));
	}

	protected abstract VoxelShape[] getUpperShapes();

	protected abstract VoxelShape[] getLowerShapes();
	
	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		int age = state.getValue(this.getAgeProperty());
		return state.getValue(HALF) == DoubleBlockHalf.UPPER ? this.getUpperShapes()[age] : this.getLowerShapes()[age];
	}
	
	@Override
	protected abstract IntegerProperty getAgeProperty();

	@Override
	public abstract int getMaxAge();

	@Override
	public BlockState getStateForAge(int age)
	{
		return super.getStateForAge(age).setValue(HALF, DoubleBlockHalf.LOWER);
	}

	@Override
	protected abstract ItemLike getBaseSeedId();

	@Override
	protected boolean isRandomlyTicking(BlockState state)
	{
		return super.isRandomlyTicking(state) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random)
	{
		if (!level.isAreaLoaded(pos, 1))
			return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (level.getRawBrightness(pos, 0) >= 9)
		{
			int age = state.getValue(this.getAgeProperty());
			if (age < this.getMaxAge())
			{
				float growthSpeed = CropBlock.getGrowthSpeed(state, level, pos);
				if (CommonHooks.canCropGrow(level, pos, state, random.nextInt((int) (25f / growthSpeed) + 1) == 0))
				{
					int newAge = age + 1;
					level.setBlock(pos, this.getStateForAge(newAge), 2);
					level.setBlock(pos.above(), this.getStateForAge(newAge).setValue(HALF, DoubleBlockHalf.UPPER), 2);
					CommonHooks.fireCropGrowPost(level, pos, state);
				}
			}
		}
	}

	@Override
	public void growCrops(Level level, BlockPos pos, BlockState state)
	{
		int newAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
		if (newAge > this.getMaxAge())
			newAge = this.getMaxAge();

		if (state.getValue(HALF) == DoubleBlockHalf.UPPER)
		{
			level.setBlock(pos.below(), this.getStateForAge(newAge), 2);
			level.setBlock(pos, this.getStateForAge(newAge).setValue(HALF, DoubleBlockHalf.UPPER), 2);
		} else
		{
			level.setBlock(pos, this.getStateForAge(newAge), 2);
			level.setBlock(pos.above(), this.getStateForAge(newAge).setValue(HALF, DoubleBlockHalf.UPPER), 2);
		}
	}
	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(HALF) != half)
		{
			return half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos)
					? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
		} else
			return Blocks.AIR.defaultBlockState();
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockPos clickedPos = context.getClickedPos();
		Level level = context.getLevel();
		return clickedPos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(clickedPos.above()).canBeReplaced(context)
				? super.getStateForPlacement(context) : null;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		BlockPos posAbove = pos.above();
		level.setBlock(posAbove, DoublePlantBlock.copyWaterloggedFrom(level, posAbove, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)), 3);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER)
			return super.canSurvive(state, level, pos);
		else
		{
			BlockState stateBelow = level.getBlockState(pos.below());
			if (state.getBlock() != this)
				return super.canSurvive(state, level, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
			return stateBelow.is(this) && stateBelow.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player)
	{
		if (!level.isClientSide)
		{
			if (player.isCreative())
				DoubleCropBlock.preventDropFromBottomPart(level, pos, state, player);
			else
				Block.dropResources(state, level, pos, null, player, player.getMainHandItem());
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack)
	{
		super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
	}

	public static void preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player)
	{
		DoubleBlockHalf half = state.getValue(HALF);
		if (half == DoubleBlockHalf.UPPER)
		{
			BlockPos posBelow = pos.below();
			BlockState stateBelow = level.getBlockState(posBelow);
			if (stateBelow.is(state.getBlock()) && stateBelow.getValue(HALF) == DoubleBlockHalf.LOWER)
			{
				BlockState fluidStateBelow = stateBelow.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				level.setBlock(posBelow, fluidStateBelow, 35);
				level.levelEvent(player, 2001, posBelow, Block.getId(stateBelow));
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(HALF, this.getAgeProperty());
	}
}
