package io.github.coffeecatrailway.food.common.block.entity;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 21/07/2025
 */
public class ChoppingBoardBlockEntity extends BlockEntity implements WorldlyRecipeContainer<ChoppingBoardBlockEntity>
{
	protected final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
	private final Map<ResourceLocation, Integer> recipeAmounts = Maps.newHashMap();

	private RecipeHolder<?> recipeused = null;

	public ChoppingBoardBlockEntity(BlockPos pos, BlockState state)
	{
		super(ModBlockEntities.CHOPPING_BOARD.get(), pos, state);
	}

	public boolean placeIngredient(ItemStack stack, boolean replace, Player player)
	{
		if (this.getItem(0).isEmpty() || replace)
		{
			this.setItem(0, (player.getAbilities().instabuild ? stack.copy() : stack).split(1));
			this.markUpdated();
			return true;
		}
		return false;
	}

	public void setIngredient(ItemStack stack)
	{
		this.setItem(0, stack);
		this.markUpdated();
	}

	public ItemStack getIngredient()
	{
		return this.getItem(0);
	}

	public void dropIngredient(Player player)
	{
		ItemStack stack = this.getIngredient().copy();
		if (this.level != null)
			Containers.dropItemStack(this.level, this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), stack);
		this.placeIngredient(ItemStack.EMPTY, true, player);
	}

	@Override
	public Map<ResourceLocation, Integer> getRecipeAmounts()
	{
		return this.recipeAmounts;
	}

	@Override
	public NonNullList<ItemStack> getInventory()
	{
		return this.inventory;
	}

	@Override
	public ChoppingBoardBlockEntity getThis()
	{
		return this;
	}

	@Override
	public int[] getSlotsForFace(Direction side)
	{
		return new int[0];
	}

	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe)
	{
		WorldlyRecipeContainer.super.setRecipeUsed(recipe);
		this.recipeused = recipe;
		this.markUpdated();
	}

	@Override
	public @Nullable RecipeHolder<?> getRecipeUsed()
	{
		return this.recipeused;
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider levelRegistry)
	{
		super.loadAdditional(tag, levelRegistry);
		WorldlyRecipeContainer.super.load(tag, levelRegistry);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
	{
		super.saveAdditional(tag, registries);
		WorldlyRecipeContainer.super.save(tag, registries);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries)
	{
//		return WorldlyRecipeContainer.saveEveryItem(new CompoundTag(), this.inventory, registries);
		return ContainerHelper.saveAllItems(new CompoundTag(), this.inventory, registries);
	}

	@Override
	public @Nullable Packet<ClientGamePacketListener> getUpdatePacket()
	{
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
