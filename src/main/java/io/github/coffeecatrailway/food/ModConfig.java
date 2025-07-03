package io.github.coffeecatrailway.food;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = FoodMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModConfig
{
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

	private static final ModConfigSpec.BooleanValue ALLOW_POTIONS_SPEC = BUILDER
			.comment("Whether sandwiches can have potions or not. WIP")
			.define("allowPotions", true);

	private static final ModConfigSpec.DoubleValue COOKED_FOOD_MODIFIER_SPEC = BUILDER
			.comment("Applied to food combo items when cooked.")
			.defineInRange("cookedFoodModifier", 1.5d, .5d, 10.d);

	private static final ModConfigSpec.BooleanValue INGREDIENT_ROTATIONS_SPEC = BUILDER
			.comment("Whether or not sandwich ingredients should be rotated.")
			.define("ingredientRotations", true);

	private static final ModConfigSpec.BooleanValue SHOW_FOOD_INFO_SPEC = BUILDER
			.comment("Whether or not to show nutrition & saturation in sandwich tooltips.")
			.define("showFoodInfo", true);

	private static final ModConfigSpec.BooleanValue INGREDIENT_AVERAGE_SPEC = BUILDER
			.comment("If true, sandwich ingredient nutrition & saturation are averaged. If false, sandwich ingredient nutrition & saturation are multiplied by the modifiers below.")
			.define("ingredientAverage", true);

	private static final ModConfigSpec.DoubleValue NUTRITION_MODIFIER_SPEC = BUILDER
			.comment("Combined nutrition modifier.")
			.defineInRange("nutritionModifier", .5d, 0d, 10.d);

	private static final ModConfigSpec.DoubleValue SATURATION_MODIFIER_SPEC = BUILDER
			.comment("Combined saturation modifier.")
			.defineInRange("saturationModifier", .5d, 0d, 10.d);

	static final ModConfigSpec SPEC = BUILDER.build();

	public static boolean ALLOW_POTIONS = false;
	public static double COOKED_FOOD_MODIFIER = 1.5d;

	public static boolean INGREDIENT_ROTATIONS = true;
	public static boolean SHOW_FOOD_INFO = true;

	public static boolean INGREDIENT_AVERAGE = false;
	public static double NUTRITION_MODIFIER = .5d;
	public static double SATURATION_MODIFIER = .5d;

	@SubscribeEvent
	static void onLoad(final ModConfigEvent event)
	{
		ALLOW_POTIONS = ALLOW_POTIONS_SPEC.getAsBoolean();
		COOKED_FOOD_MODIFIER = COOKED_FOOD_MODIFIER_SPEC.getAsDouble();

		INGREDIENT_ROTATIONS = INGREDIENT_ROTATIONS_SPEC.getAsBoolean();
		SHOW_FOOD_INFO = SHOW_FOOD_INFO_SPEC.getAsBoolean();

		INGREDIENT_AVERAGE = INGREDIENT_AVERAGE_SPEC.getAsBoolean();
		NUTRITION_MODIFIER = NUTRITION_MODIFIER_SPEC.getAsDouble();
		SATURATION_MODIFIER = SATURATION_MODIFIER_SPEC.getAsDouble();
	}
}
