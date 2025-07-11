package io.github.coffeecatrailway.food.common.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author CoffeeCatRailway
 * Created: 09/07/2025
 */
public class PopcornItem extends Item
{
	public PopcornItem(Properties properties)
	{
		super(properties.craftRemainder(ModItems.POPCORN_BAG.get()).stacksTo(32));
	}

	/**
	 * Copied from {@link net.minecraft.world.item.HoneyBottleItem}
	 */
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
	{
		super.finishUsingItem(stack, level, entity);
		if (entity instanceof ServerPlayer)
		{
			ServerPlayer player = (ServerPlayer) entity;
			CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
			player.awardStat(Stats.ITEM_USED.get(this));
		}

		if (stack.isEmpty())
			return new ItemStack(ModItems.POPCORN_BAG.get());
		else
		{
			if (entity instanceof Player && !((Player) entity).getAbilities().instabuild)
			{
				ItemStack itemstack = new ItemStack(ModItems.POPCORN_BAG.get());
				Player player = (Player) entity;
				if (!player.getInventory().add(itemstack))
					player.drop(itemstack, false);
			}
			return stack;
		}
	}
}
