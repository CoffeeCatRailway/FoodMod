package io.github.coffeecatrailway.food.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * @author CoffeeCatRailway
 * Created: 05/07/2025
 */
public class FoodConfigServer
{
	public final ModConfigSpec.BooleanValue allowPotions;
	public final ModConfigSpec.DoubleValue toastedModifier;
	public final ModConfigSpec.BooleanValue averageIngredients;
	public final ModConfigSpec.DoubleValue nutritionModifier;
	public final ModConfigSpec.DoubleValue saturationModifier;

	public FoodConfigServer(ModConfigSpec.Builder builder)
	{
		builder.comment("Server-side config options").push("sandwich");

		allowPotions = builder.comment("If true, potions can be included in sandwiches.")
				.define("allowPotions", true);

		toastedModifier = builder.comment("Nutrition & saturation are multiplied by this when toasted/cooked.")
				.defineInRange("toastedModifier", 1.3d, .5d, 10.d);

		averageIngredients = builder.comment("If true, ingredient nutrition & saturation are averaged together.",
						"If false, ingredient nutrition & saturation are summed together then multiplied by 'combined modifiers'.")
				.define("averageIngredients", true);

		builder.push("combinedModifiers");
		nutritionModifier = builder.comment("Nutrition modifier.")
				.defineInRange("nutritionModifier", .6d, 0d, 10.d);

		saturationModifier = builder.comment("Saturation modifier.")
				.defineInRange("saturationModifier", .5d, 0d, 10.d);
		builder.pop(); // combinedModifiers

		builder.pop(); // sandwich
	}
}
