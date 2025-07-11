package io.github.coffeecatrailway.food.common.item;

/**
 * @author CoffeeCatRailway
 * Created: 09/07/2025
 */
public class CrackerItem extends FoodComboItem
{
	public CrackerItem(Properties properties)
	{
		super(properties.stacksTo(32), new FoodComboProperties(ModItems.CRACKER_DUMMY));
	}
}
