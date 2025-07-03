package io.github.coffeecatrailway.food.common.item;

import io.github.coffeecatrailway.food.DataGen;
import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.ModConfig;
import io.github.coffeecatrailway.food.common.ModFoods;
import io.github.coffeecatrailway.food.common.item.component.FoodComboComponent;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author CoffeeCatRailway
 * Created: 28/05/2025
 */
public class FoodComboItem extends Item
{
	public static final FoodProperties FOOD_PROP = new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(1.f).build();

	public final FoodComboProperties foodComboProperties;

	public FoodComboItem(Properties properties, FoodComboProperties foodComboProperties)
	{
		super(properties.food(FOOD_PROP).component(ModComponents.FOOD_COMBO.get(), FoodComboComponent.EMPTY));
		this.foodComboProperties = foodComboProperties;
	}

	@Override
	public Component getName(ItemStack stack)
	{
		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);
		Component name = super.getName(stack);
		if (!data.ingredients().isEmpty())
			name = data.ingredients().getFirst().getHoverName().copy().append(" ").append(name);
		return name;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
	{
		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);
		if (data.ingredients().isEmpty())
			return;

		if (Screen.hasShiftDown())
		{
			if (ModConfig.SHOW_FOOD_INFO)
			{
				FoodProperties foodProperties = this.getFood(stack, null);
				tooltipComponents.add(Component.literal("Nutrition: " + foodProperties.nutrition()).withStyle(ChatFormatting.GRAY));
				tooltipComponents.add(Component.literal("Saturation: " + foodProperties.saturation()).withStyle(ChatFormatting.GRAY));
			}

			List<String> has = new ArrayList<>();
			data.ingredients().forEach(ingredientStack ->
			{
				String id = ingredientStack.getDescriptionId();
				if (!has.contains(ingredientStack.getDescriptionId()))
				{
					int frequency = Math.toIntExact(data.ingredients().stream().map(ItemStack::getDescriptionId).filter(s -> s.equalsIgnoreCase(id)).count());
					tooltipComponents.add(Component.literal("- ").append(ingredientStack.getHoverName().copy().append(" x" + frequency)).withStyle(ChatFormatting.GRAY));
					has.add(id);
				}
			});
		} else
			tooltipComponents.add(DataGen.Language.shiftInfo(Component.translatable("item." + FoodMod.MODID + ".food_combo.info")).withStyle(ChatFormatting.DARK_GRAY));
	}

//	@Override
//	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
//	{
//		if (!level.isClientSide())
//		{
//			SoundEvent sound = SoundEvents.GENERIC_EAT;
//			Supplier<Float> pitch = () -> 1.f + (level.getRandom().nextFloat() - level.getRandom().nextFloat()) * .4f;
//
//			entity.playSound(sound, 1f, pitch.get());
//			level.playSound(entity, entity.getOnPos(), sound, SoundSource.NEUTRAL, 1f, pitch.get());
//
//			FoodProperties food = this.getFood(stack, entity);
//			if (entity instanceof Player player)
//			{
//				if (!player.isCreative())
//					stack.shrink(1);
//				player.getFoodData().eat(food);
//			}
//
//			//TODO potions, convert to items (bowls, bottles, etc)
//		}
//		stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY).ingredients().forEach(itemStack -> itemStack.finishUsingItem(level, entity));
//		return stack;
//	}

	@Override
	public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity)
	{
		return this.getFood(stack, entity);
	}

	public FoodProperties getFood(ItemStack stack, @Nullable LivingEntity entity)
	{
		List<FoodProperties> foods = new ArrayList<>();
		FoodComboComponent data = stack.getOrDefault(ModComponents.FOOD_COMBO, FoodComboComponent.EMPTY);

		FoodProperties bunFood = this.foodComboProperties.getBunFood(data.toasted(), entity);
		foods.add(bunFood);
		if (this.foodComboProperties.hasTwoBuns)
			foods.add(bunFood);

		data.ingredients().forEach(itemStack -> {
			FoodProperties foodProperties = itemStack.getFoodProperties(entity);
			if (foodProperties != null)
				foods.add(foodProperties);
		});

		if (ModConfig.INGREDIENT_AVERAGE)
			return ModFoods.average(data.toasted(), foods.toArray(new FoodProperties[0]));
		return ModFoods.combine((float) ModConfig.NUTRITION_MODIFIER, (float) ModConfig.SATURATION_MODIFIER, data.toasted(), foods.toArray(new FoodProperties[0]));
	}

	public static class FoodComboProperties
	{
		private final ItemStack bunItem;
		private final ItemStack toastedBunItem;

		private boolean hasTwoBuns = false;
		private boolean canBeToasted = false;

		public FoodComboProperties(ItemLike bunItem)
		{
			this(bunItem, bunItem);
		}

		public FoodComboProperties(ItemLike bunItem, ItemLike toastedBunItem)
		{
			this.bunItem = new ItemStack(bunItem);
			this.toastedBunItem = new ItemStack(toastedBunItem);
		}

		public FoodComboProperties hasTwoBuns()
		{
			this.hasTwoBuns = true;
			return this;
		}

		public FoodComboProperties canBeToasted()
		{
			this.canBeToasted = true;
			return this;
		}

		@Nullable
		public FoodProperties getBunFood(boolean toasted, @Nullable LivingEntity entity)
		{
			return (this.canBeToasted && toasted) ? this.toastedBunItem.getFoodProperties(entity) : this.bunItem.getFoodProperties(entity);
		}

		public ItemStack getBunItem(boolean toasted)
		{
			return toasted ? this.toastedBunItem : this.bunItem;
		}

		public boolean isHasTwoBuns()
		{
			return this.hasTwoBuns;
		}

		public boolean isCanBeToasted()
		{
			return this.canBeToasted;
		}
	}
}
