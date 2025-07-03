package io.github.coffeecatrailway.food.common;

import com.google.common.collect.ImmutableList;
import io.github.coffeecatrailway.food.ModConfig;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

import java.util.List;
import java.util.Optional;

/**
 * @author CoffeeCatRailway
 * Created: 03/07/2025
 */
public class ModFoods
{
	public static final int SLICE_PER_BREAD = 4;
	public static final FoodProperties FOOD_BREAD_SLICE = new FoodProperties.Builder().nutrition(Foods.BREAD.nutrition() / SLICE_PER_BREAD).saturationModifier(Foods.BREAD.saturation() / (float) SLICE_PER_BREAD).build();
	public static final FoodProperties FOOD_TOAST = new FoodProperties.Builder().nutrition((int) (FOOD_BREAD_SLICE.nutrition() * 1.5f)).saturationModifier(FOOD_BREAD_SLICE.saturation() * 1.5f).build();

	public static FoodProperties average(boolean cooked, FoodProperties... foods)
	{
		float avg = 1.f / foods.length;
		return combine(avg, avg, cooked, foods);
	}

	public static FoodProperties combine(float nutritionMod, float saturationMod, boolean cooked, FoodProperties... foods)
	{
		float nutrition = 0.f;
		float saturation = 0.f;
		float eatSeconds = 0.f;
		ImmutableList.Builder<FoodProperties.PossibleEffect> effects = ImmutableList.builder();

		for (FoodProperties prop: foods)
		{
			nutrition += prop.nutrition();
			saturation += prop.saturation();
			eatSeconds += prop.eatSeconds();
			effects.addAll(prop.effects());
		}

		nutrition *= nutritionMod;
		saturation *= saturationMod;
		eatSeconds *= 1.f / foods.length;

		if (cooked)
		{
			nutrition *= (float) ModConfig.COOKED_FOOD_MODIFIER;
			saturation *= (float) ModConfig.COOKED_FOOD_MODIFIER;
		}
		return new FoodProperties(Math.round(nutrition), saturation, false, eatSeconds, Optional.empty(), effects.build());
	}
}
