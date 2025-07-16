package io.github.coffeecatrailway.food.common.item;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 09/07/2025
 */
public class PizzaItem extends FoodComboItem
{
	public PizzaItem(Properties properties)
	{
		super(properties.stacksTo(16), new FoodComboProperties(ModItems.UNFIRED_PIZZA_BASE, ModItems.FIRED_PIZZA_BASE).canBeToasted());
	}

	@Override
	public Component getName(ItemStack stack)
	{
		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);
		Component name = super.getName(stack);
		if (data.toasted())
			name = Component.translatable("item." + FoodMod.MODID + ".pizza.fired").append(" ").append(name);
		else
			name = Component.translatable("item." + FoodMod.MODID + ".pizza.unfired").append(" ").append(name);
		return name;
	}
}
