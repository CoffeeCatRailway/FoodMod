package io.github.coffeecatrailway.food.common.item.crafting;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

/**
 * @author CoffeeCatRailway
 * Created: 31/05/2025
 */
public class ModRecipes
{
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, FoodMod.MODID);

	public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<SandwichRecipe>> SANDWICH_SERIALIZER = SERIALIZERS.register("sandwich", () -> new SimpleCraftingRecipeSerializer<>(SandwichRecipe::new));

	public static void init(IEventBus modEventBus)
	{
		LOGGER.info("Registering recipe serializers");
		SERIALIZERS.register(modEventBus);
	}
}
