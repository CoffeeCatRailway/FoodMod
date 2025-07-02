package io.github.coffeecatrailway.food.common.item;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

/**
 * @author CoffeeCatRailway
 * Created: 30/05/2025
 */
public class SandwichItem extends FoodComboItem
{
	public SandwichItem(Properties properties)
	{
		super(properties.stacksTo(16), new FoodComboProperties(ModItems.BREAD_SLICE, ModItems.TOAST).hasTwoBuns().canBeToasted());
	}

	@Override
	public Component getName(ItemStack stack)
	{
		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);
		Component name = super.getName(stack);
		if (data.toasted())
			name = Component.translatable("item." + FoodMod.MODID + ".sandwich.toasted").append(" ").append(name);
		return name;
	}
}
