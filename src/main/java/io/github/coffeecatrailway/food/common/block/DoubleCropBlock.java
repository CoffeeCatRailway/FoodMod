package io.github.coffeecatrailway.food.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.EventHooks;

/**
 * @author CoffeeCatRailway
 * Created: 11/07/2025
 */
public abstract class DoubleCropBlock extends BushBlock implements BonemealableBlock
{
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	public DoubleCropBlock(Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	public abstract IntegerProperty getAgeProperty();

	public abstract int getMaxAge();

	protected abstract VoxelShape[] getTopShapes();

	protected abstract VoxelShape[] getBottomShapes();

	protected abstract boolean placeableOn(BlockState state, BlockGetter level, BlockPos pos);

	protected abstract boolean needsFertileLand();

	protected abstract Item getPickBlock(BlockState state);

	protected abstract void applyBonemeal(int age, ServerLevel level, BlockPos pos, BlockState state);

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return switch (state.getValue(HALF))
		{
			case UPPER -> this.getTopShapes()[state.getValue(this.getAgeProperty())];
			case LOWER -> this.getBottomShapes()[state.getValue(this.getAgeProperty())];
		};
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos)
	{
		BlockPos above = pos.above(2);
		return this.placeableOn(state, level, pos) && (level.getBlockState(above).isAir() || level.getBlockState(above).getBlock() == this);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
	{
		BlockPos below = pos.below();
		BlockState stateBelow = level.getBlockState(below);
		boolean checkLight = level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos);
		boolean checkOtherState = state.getBlock() == this && stateBelow.getBlock() == this && state.getValue(HALF) == DoubleBlockHalf.UPPER && stateBelow.getValue(HALF) == DoubleBlockHalf.LOWER;
		return checkLight && (this.mayPlaceOn(stateBelow, level, below) || checkOtherState);
	}

	protected BlockState getStateForAge(int age, DoubleBlockHalf half)
	{
		return this.defaultBlockState().setValue(this.getAgeProperty(), age).setValue(HALF, half);
	}

	protected boolean isYoung(BlockState state)
	{
		return state.getValue(this.getAgeProperty()) < this.getMaxAge();
	}

//	protected float getGrowthSpeed(ServerLevel level, BlockPos pos)
//	{
//		float speed = 1.125f;
//		if (level.canSeeSky(pos))
//			speed += 2f;
//		if (this.needsFertileLand() && (this.isFertile(level.getBlockState(pos.below())) || this.isFertile(level.getBlockState(pos.below(2)))))
//			speed *= 1.5f;
//		return speed;
//	}

	// From net.minecraft.world.level.block.CropBlock
	protected float getGrowthSpeed(BlockState state, BlockGetter blockGetter, BlockPos pos) {
		Block block = state.getBlock();
		float speed = 1f;
		BlockPos posBelow = pos.below();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				float speedDelta = 0f;
				BlockState stateBelow = blockGetter.getBlockState(posBelow.offset(i, 0, j));
				TriState soilDecision = stateBelow.canSustainPlant(blockGetter, posBelow.offset(i, 0, j), Direction.UP, state);
				if (soilDecision.isDefault() ? stateBelow.getBlock() instanceof FarmBlock : soilDecision.isTrue()) {
					speedDelta = 1f;
					if (stateBelow.isFertile(blockGetter, pos.offset(i, 0, j)))
						speedDelta = 3f;
				}

				if (i != 0 || j != 0)
					speedDelta /= 4f;

				speed += speedDelta;
			}
		}

		BlockPos posNorth = pos.north();
		BlockPos posSouth = pos.south();
		BlockPos posWest = pos.west();
		BlockPos posEast = pos.east();
		boolean isEastWest = blockGetter.getBlockState(posWest).is(block) || blockGetter.getBlockState(posEast).is(block);
		boolean isNorthSouth = blockGetter.getBlockState(posNorth).is(block) || blockGetter.getBlockState(posSouth).is(block);
		if (isEastWest && isNorthSouth) {
			speed /= 2f;
		} else {
			boolean flag2 = blockGetter.getBlockState(posWest.north()).is(block)
							|| blockGetter.getBlockState(posEast.north()).is(block)
							|| blockGetter.getBlockState(posEast.south()).is(block)
							|| blockGetter.getBlockState(posWest.south()).is(block);
			if (flag2) // WHAT??
				speed /= 2f;
		}

		return speed;
	}

	private boolean isFertile(BlockState state)
	{
		return state.is(Blocks.FARMLAND) && state.getValue(FarmBlock.MOISTURE) > 0;
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if (entity instanceof Ravager && EventHooks.canEntityGrief(level, entity))
			level.destroyBlock(pos, true, entity);
		super.entityInside(state, level, pos, entity);
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player)
	{
		return new ItemStack(this.getPickBlock(state));
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state)
	{
		return this.isYoung(state);
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state)
	{
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
	{
		int age = state.getValue(this.getAgeProperty()) + Mth.nextInt(random, 2, 3);
		if (age > this.getMaxAge())
			age = this.getMaxAge();
		this.applyBonemeal(age, level, pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(this.getAgeProperty()).add(HALF);
	}
}
