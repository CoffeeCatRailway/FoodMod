package io.github.coffeecatrailway.food.common.item.crafting;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
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
	private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, FoodMod.MODID);
	private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, FoodMod.MODID);

	public static final DeferredHolder<RecipeType<?>, RecipeType<ChoppingBoardRecipe>> CHOPPING_BOARD_TYPE = registerType("chopping_board", ChoppingBoardRecipe.class);
	public static final DeferredHolder<RecipeSerializer<?>, ChoppingBoardRecipe.Serializer> CHOPPING_BOARD_SERIALIZER = SERIALIZERS.register("chopping_board", ChoppingBoardRecipe.Serializer::new);

	public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<SandwichRecipe>> SANDWICH_SERIALIZER = SERIALIZERS.register("sandwich", () -> new SimpleCraftingRecipeSerializer<>(SandwichRecipe::new));
	public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<CrackerRecipe>> CRACKER_SERIALIZER = SERIALIZERS.register("cracker", () -> new SimpleCraftingRecipeSerializer<>(CrackerRecipe::new));
	public static final DeferredHolder<RecipeSerializer<?>, SimpleCraftingRecipeSerializer<PizzaRecipe>> PIZZA_SERIALIZER = SERIALIZERS.register("pizza", () -> new SimpleCraftingRecipeSerializer<>(PizzaRecipe::new));

	private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> registerType(String id, Class<T> clazz)
	{
		return TYPES.register(id, () -> RecipeType.<T>simple(ResourceLocation.fromNamespaceAndPath(FoodMod.MODID, id)));
	}

	public static void init(IEventBus modEventBus)
	{
		LOGGER.debug("Registering recipe serializers");
		TYPES.register(modEventBus);
		SERIALIZERS.register(modEventBus);
	}
}
