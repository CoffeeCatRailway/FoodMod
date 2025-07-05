package io.github.coffeecatrailway.food.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * @author CoffeeCatRailway
 * Created: 05/07/2025
 */
public class FoodConfigClient
{
	public final ModConfigSpec.BooleanValue rotateIngredients;
	public final ModConfigSpec.BooleanValue showNutritionSaturation;

	public FoodConfigClient(ModConfigSpec.Builder builder)
	{
		builder.comment("Client-side config options").push("sandwich");

		rotateIngredients = builder.comment("Looks funny if they're aligned.")
				.define("rotateIngredients", true);

		showNutritionSaturation = builder.comment("Shows nutrition & saturation values in tooltip when SHIFT is held.")
				.define("showNutritionSaturation", false);

		builder.pop(); // sandwich
	}
}
