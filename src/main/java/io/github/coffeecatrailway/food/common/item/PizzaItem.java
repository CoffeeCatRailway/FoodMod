package io.github.coffeecatrailway.food.common.item;

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
}
