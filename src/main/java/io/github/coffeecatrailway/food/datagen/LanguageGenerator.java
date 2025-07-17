package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class LanguageGenerator extends LanguageProvider
{
	public static final Map<Supplier<? extends Item>, String> ITEMS = new HashMap<>();
	public static final Map<Supplier<? extends Block>, String> BLOCKS = new HashMap<>();

	public LanguageGenerator(PackOutput output)
	{
		super(output, FoodMod.MODID, "en_us");
	}

	@Override
	protected void addTranslations()
	{
		this.add("item." + FoodMod.MODID + ".green_food.drseuss", "Yay to Dr. Seuss for his green eggs and ham!");
		this.add("item." + FoodMod.MODID + ".green_food.birthday_line1", "And a happy birthday to the great Dr. Seuss!");
		this.add("item." + FoodMod.MODID + ".green_food.birthday_line2", "Born 2 March 1904, Died 24 September 1991");

		this.add("item." + FoodMod.MODID + ".food_combo.info", "to show ingredients");
		this.add("item." + FoodMod.MODID + ".food_combo.info.nutrition", "Nutrition: %s");
		this.add("item." + FoodMod.MODID + ".food_combo.info.saturation", "Saturation: %s");
		this.add("item." + FoodMod.MODID + ".sandwich.toasted", "Toasted");
		this.add("item." + FoodMod.MODID + ".pizza.unfired", "Unfired");
		this.add("item." + FoodMod.MODID + ".pizza.fired", "Fired");

		// Other
		this.add("tooltip." + FoodMod.MODID + ".hold_shift", "[HOLD SHIFT %s]");

		// Config
		this.add(FoodMod.MODID + ".configuration.sandwich", "Sandwich");
		this.addConfig(FoodConfigs.CLIENT.rotateIngredients, "Rotate Ingredients");
		this.addConfig(FoodConfigs.CLIENT.showNutritionSaturation, "Show Nutrition/Saturation");

		this.addConfig(FoodConfigs.SERVER.allowPotions, "Allow Potions");
		this.addConfig(FoodConfigs.SERVER.toastedModifier, "Toasted Modifier");
		this.addConfig(FoodConfigs.SERVER.averageIngredients, "Average Ingredients");
		this.add(FoodMod.MODID + ".configuration.combinedModifiers", "Combined Modifiers");
		this.addConfig(FoodConfigs.SERVER.nutritionModifier, "Nutrition Modifier");
		this.addConfig(FoodConfigs.SERVER.saturationModifier, "Saturation Modifier");
		this.addConfig(FoodConfigs.SERVER.crackedEggSpawnChance, "Cracked Egg Spawn Chance");

		ITEMS.forEach(this::addItem);
		BLOCKS.forEach(this::addBlock);
	}

	private void addConfig(ModConfigSpec.ConfigValue<?> config, String name)
	{
		this.add(FoodMod.MODID + ".configuration." + config.getPath().getLast(), name);
	}

	public static MutableComponent shiftInfo(Object info)
	{
		return Component.translatable("tooltip." + FoodMod.MODID + ".hold_shift", info);
	}

	public static MutableComponent getFlavour(int flavour)
	{
		return Component.translatable("container." + FoodMod.MODID + ".popcorn_machine.flavour", flavour);
	}

	public static MutableComponent getPopcorn(int popcorn)
	{
		return Component.translatable("container." + FoodMod.MODID + ".popcorn_machine.popcorn", popcorn);
	}

	public static String capitalize(String id)
	{
		String[] names = id.split("_");
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (String name : names)
		{
			builder.append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
			i++;
			if (i != names.length)
				builder.append(" ");
		}
		return builder.toString();
	}
}
