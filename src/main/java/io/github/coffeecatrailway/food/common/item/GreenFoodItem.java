package io.github.coffeecatrailway.food.common.item;

import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 09/07/2025
 */
public class GreenFoodItem extends Item
{
	public GreenFoodItem(Properties properties)
	{
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
	{
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		tooltipComponents.add(Component.translatable("item." + FoodMod.MODID + ".green_food.drseuss"));
		if (this.isDate(Calendar.MARCH, 2))
		{
			tooltipComponents.add(Component.translatable("item." + FoodMod.MODID + ".green_food.birthday_line1"));
			tooltipComponents.add(Component.translatable("item." + FoodMod.MODID + ".green_food.birthday_line2"));
		}
	}

	private boolean isDate(int month, int day)
	{
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);

		int curentMonth = cal.get(Calendar.MONTH);
		int curentDay = cal.get(Calendar.DAY_OF_MONTH);
		boolean inRange = curentDay == day;

		return (curentMonth == month && inRange);
	}
}
