package io.github.coffeecatrailway.food.common;

import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 03/07/2025
 */
public class ModFoods
{
	private static final Supplier<MobEffectInstance> HUNGER_EFFECT = () -> new MobEffectInstance(MobEffects.HUNGER, 400, 1);
	private static final Supplier<MobEffectInstance> HARM_EFFECT = () -> new MobEffectInstance(MobEffects.HARM, 1, 2);

	public static final FoodProperties CHEESE_BLOCK = new FoodProperties.Builder().nutrition(8).saturationModifier(.4f).build();
	public static final FoodProperties CHEESE_SLICE = dividef(CHEESE_BLOCK, 4f, 2f).build();

	public static final FoodProperties BLUE_CHEESE_BLOCK = addf(CHEESE_BLOCK, 1, .1f).build();
	public static final FoodProperties BLUE_CHEESE_SLICE = dividef(BLUE_CHEESE_BLOCK, 4f, 2f).build();

	public static final FoodProperties GOUDA_CHEESE_BLOCK = addf(CHEESE_BLOCK, 0, .25f).build();
	public static final FoodProperties GOUDA_CHEESE_SLICE = dividef(GOUDA_CHEESE_BLOCK, 4f, 2f).build();

	public static final FoodProperties SWISS_CHEESE_BLOCK = addf(CHEESE_BLOCK, -1, -.1f).build();
	public static final FoodProperties SWISS_CHEESE_SLICE = dividef(SWISS_CHEESE_BLOCK, 4f, 2f).build();
	public static final FoodProperties SWISS_CHEESE_BITES = dividef(SWISS_CHEESE_SLICE, 2f, 1f).fast().build();

	public static final FoodProperties GOAT_CHEESE_BLOCK = timesf(CHEESE_BLOCK, 1.25f).build();
	public static final FoodProperties GOAT_CHEESE_SLICE = dividef(GOAT_CHEESE_BLOCK, 4f, 2f).build();

	public static final FoodProperties GENERAL_INGREDIENT = new FoodProperties.Builder().nutrition(1).saturationModifier(.3f).build();
	public static final FoodProperties DOUGH = timesf(GENERAL_INGREDIENT, 3f).build();

	public static final FoodProperties PIZZA = timesf(DOUGH, 1.5f).build();

	public static final int SLICE_PER_BREAD = 3;
	public static final FoodProperties BREAD_SLICE = dividef(Foods.BREAD, SLICE_PER_BREAD).build();
	public static final FoodProperties TOAST = timesf(BREAD_SLICE, 1.5f).build();
	public static final FoodProperties MOLDY_BREAD_SLICE = timesf(BREAD_SLICE, .75f).effect(HUNGER_EFFECT, 1f).build();

	public static final FoodProperties CRACKER = timesf(DOUGH, 1.25f).build();

	public static final FoodProperties CRACKED_EGG = new FoodProperties.Builder().nutrition(3).saturationModifier(.2f).build();
	public static final FoodProperties FRIED_EGG = timesf(CRACKED_EGG, 2f).build();
	public static final FoodProperties GREEN_CRACKED_EGG = timesf(CRACKED_EGG, 1f).effect(HUNGER_EFFECT, 1f).build();

	public static final FoodProperties HAM_SLICE = dividef(Foods.PORKCHOP, 2.5f).build();
	public static final FoodProperties COOKED_HAM_SLICE = dividef(Foods.COOKED_PORKCHOP, 2.5f).build();
	public static final FoodProperties GREEN_HAM_SLICE = timesf(HAM_SLICE, 1f).effect(HUNGER_EFFECT, 1f).build();

	public static final FoodProperties RAW_BACON = timesf(HAM_SLICE, 1.5f).build();
	public static final FoodProperties COOKED_BACON = timesf(COOKED_HAM_SLICE, 1.25f).build();

	private static final FoodProperties PINEAPPLE_NO_EFFECT = new FoodProperties.Builder().nutrition(10).saturationModifier(.5f).build();
	public static final FoodProperties PINEAPPLE = timesf(PINEAPPLE_NO_EFFECT, 1f).effect(HARM_EFFECT, 1f).build();
	public static final FoodProperties PINEAPPLE_RING = dividef(PINEAPPLE_NO_EFFECT, 4f).build();
	public static final FoodProperties PINEAPPLE_BITES = dividef(PINEAPPLE_RING, 3f).build();

	public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(4).saturationModifier(.3f).build();
	public static final FoodProperties TOMATO_SLICE = dividef(TOMATO, 2f).build();
	public static final FoodProperties TOMATO_SAUCE = combine(.5f, .5f, false, TOMATO, GENERAL_INGREDIENT, GENERAL_INGREDIENT);

	public static final FoodProperties CORN_COB = new FoodProperties.Builder().nutrition(8).saturationModifier(.5f).build();
	public static final FoodProperties CORN_KERNELS = dividef(CORN_COB, 8f, 2f).build();

	public static final FoodProperties POPCORN = timesf(combine(1f, 1f, false, CORN_KERNELS, CORN_KERNELS, GENERAL_INGREDIENT), 1.25f, .75f).build();
	public static final FoodProperties CHEESY_POPCORN = timesf(combine(1f, 1f, false, POPCORN, CHEESE_SLICE), 1f, .5f).build();
	public static final FoodProperties CARAMEL_POPCORN = timesf(combine(1f, 1f, false, POPCORN, GENERAL_INGREDIENT), 1f, .5f).build();

	public static final FoodProperties MOUSE = new FoodProperties.Builder().nutrition(2).saturationModifier(.3f).effect(HUNGER_EFFECT, .3f).build();
	public static final FoodProperties COOKED_MOUSE = new FoodProperties.Builder().nutrition(4).saturationModifier(.6f).build();

	public static final FoodProperties FOOD_SCRAPS = new FoodProperties.Builder().nutrition(1).saturationModifier(.2f).build();

	public static final FoodProperties MAPLE_SAP_BOTTLE = new FoodProperties.Builder().nutrition(6).saturationModifier(.1f).build();
	public static final FoodProperties MAPLE_SYRUP = combine(1f, 1f, false, MAPLE_SAP_BOTTLE, GENERAL_INGREDIENT);
	public static final FoodProperties MAPLE_POPCORN = timesf(combine(1f, 1f, false, MAPLE_SYRUP, GENERAL_INGREDIENT), 1f, .5f).build();

//	public static final FoodProperties CROISSANT = timesf(DOUGH, 1.25f).build();
//	public static final FoodProperties CHEESY_CROISSANT = combine(1f, .75f, false, CROISSANT, CHEESE_SLICE);
//	public static final FoodProperties CHEESY_HAM_CROISSANT = combine(1f, .75f, false, CROISSANT, CHEESE_SLICE, HAM_SLICE);

	private static FoodProperties.Builder addf(FoodProperties copy, int nutrition, float saturationMod)
	{
		float modifier = modifierBySaturation(copy.nutrition(), copy.saturation()) + saturationMod;
		return copy(copy).nutrition(copy.nutrition() + nutrition).saturationModifier(modifier);
	}

	private static FoodProperties.Builder dividef(FoodProperties copy, float amount)
	{
		return dividef(copy, amount, amount);
	}

	private static FoodProperties.Builder dividef(FoodProperties copy, float nutrition, float saturationMod)
	{
		float modifier = modifierBySaturation(copy.nutrition(), copy.saturation()) / saturationMod;
		return copy(copy).nutrition(Mth.ceil((float) copy.nutrition() / nutrition)).saturationModifier(modifier);
	}

	private static FoodProperties.Builder timesf(FoodProperties copy, float amount)
	{
		return timesf(copy, amount, amount);
	}

	private static FoodProperties.Builder timesf(FoodProperties copy, float nutrition, float saturationMod)
	{
		float modifier = modifierBySaturation(copy.nutrition(), copy.saturation()) * saturationMod;
		return copy(copy).nutrition(Mth.ceil((float) copy.nutrition() * nutrition)).saturationModifier(modifier);
	}

	private static float modifierBySaturation(int nutrition, float saturation)
	{
		return saturation / 2f / (float) nutrition;
	}

	private static FoodProperties.Builder copy(FoodProperties toCopy)
	{
		FoodProperties.Builder copy = new FoodProperties.Builder();
		if (toCopy.canAlwaysEat())
			copy = copy.alwaysEdible();
		if (toCopy.eatSeconds() < 1f)
			copy = copy.fast();

		for (FoodProperties.PossibleEffect effect : toCopy.effects())
			copy = copy.effect(effect.effectSupplier(), effect.probability());

		return copy;
	}

	public static FoodProperties average(boolean cooked, FoodProperties... foods)
	{
		float avg = 1f / foods.length;
		return combine(avg, avg, cooked, foods);
	}

	public static FoodProperties combine(float nutritionMod, float saturationMod, boolean cooked, FoodProperties... foods)
	{
		float nutrition = 0f;
		float saturation = 0f;
		float eatSeconds = 0f;
		List<FoodProperties.PossibleEffect> effects = new ArrayList<>();

		for (FoodProperties prop: foods)
		{
			nutrition += prop.nutrition();
			saturation += prop.saturation();
			eatSeconds += prop.eatSeconds();

			for (FoodProperties.PossibleEffect effect : prop.effects())
				if (!effects.contains(effect))
					effects.add(effect);
		}

		nutrition *= nutritionMod;
		saturation *= saturationMod;
		eatSeconds *= 1f / foods.length;

		if (cooked)
		{
			nutrition *= (float) FoodConfigs.SERVER.toastedModifier.getAsDouble();
			saturation *= (float) FoodConfigs.SERVER.toastedModifier.getAsDouble();
		}
		return new FoodProperties(Mth.ceil(nutrition), saturation, false, eatSeconds, Optional.empty(), effects);
	}
}
