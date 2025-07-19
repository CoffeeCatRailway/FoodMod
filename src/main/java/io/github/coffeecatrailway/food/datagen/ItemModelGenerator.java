package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class ItemModelGenerator extends ItemModelProvider
{
	public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
	{
		super(output, FoodMod.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		// Crafting Materials
		this.basicItem(ModItems.GEAR_WOODEN.get());
		this.basicItem(ModItems.GEAR_STONE.get());

		// Tools
		this.basicItem(ModItems.CURDLER_WOODEN.get());
		this.basicItem(ModItems.CURDLER_STONE.get());

		this.basicItem(ModItems.ROLLING_PIN_WOODEN.get());
		this.basicItem(ModItems.ROLLING_PIN_STONE.get());

		this.basicItem(ModItems.GRIND_STONES.get());

		this.basicItem(ModItems.KNIFE_WOODEN.get());
		this.basicItem(ModItems.KNIFE_STONE.get());
		this.basicItem(ModItems.KNIFE_COPPER.get());
		this.basicItem(ModItems.KNIFE_GOLD.get());
		this.basicItem(ModItems.KNIFE_IRON.get());
		this.basicItem(ModItems.KNIFE_DIAMOND.get());
		this.basicItem(ModItems.KNIFE_NETHERITE.get());

		// Foods
		this.basicItem(ModItems.CHEESE_SLICE.get());
		this.basicItem(ModItems.BLUE_CHEESE_SLICE.get());
		this.basicItem(ModItems.GOUDA_CHEESE_SLICE.get());
		this.basicItem(ModItems.SWISS_CHEESE_SLICE.get());
		this.basicItem(ModItems.SWISS_CHEESE_BITES.get());
		this.basicItem(ModItems.GOAT_CHEESE_SLICE.get());

		this.basicItem(ModItems.SALT.get());
		this.basicItem(ModItems.FLOUR.get());

		this.basicItem(ModItems.DOUGH.get());

		this.basicItem(ModItems.UNFIRED_PIZZA_BASE.get());
		this.basicItem(ModItems.FIRED_PIZZA_BASE.get());

		this.basicItem(ModItems.UNBAKED_BREAD.get());
		this.basicItem(ModItems.BREAD_SLICE.get());
		this.basicItem(ModItems.TOAST.get());
		this.basicItem(ModItems.MOLDY_BREAD_SLICE.get());

		this.basicItem(ModItems.UNBAKED_CRACKER.get());
		this.basicItem(ModItems.CRACKER_DUMMY.get(), FoodMod.id("item/cracker"));

		this.basicItem(ModItems.CRACKED_EGG.get());
		this.basicItem(ModItems.FRIED_EGG.get());
		this.basicItem(ModItems.GREEN_CRACKED_EGG.get());

		this.basicItem(ModItems.HAM_SLICE.get());
		this.basicItem(ModItems.COOKED_HAM_SLICE.get());
		this.basicItem(ModItems.GREEN_HAM_SLICE.get());

		this.basicItem(ModItems.RAW_BACON.get());
		this.basicItem(ModItems.COOKED_BACON.get());

		this.basicItem(ModItems.PINEAPPLE_CROWN.get());
		this.basicItem(ModItems.PINEAPPLE.get());
		this.basicItem(ModItems.PINEAPPLE_RING.get());
		this.basicItem(ModItems.PINEAPPLE_BITES.get());

		this.basicItem(ModItems.TOMATO_SEEDS.get());
		this.basicItem(ModItems.TOMATO.get());
		this.basicItem(ModItems.TOMATO_SLICE.get());
		this.basicItem(ModItems.TOMATO_SAUCE.get());

		this.basicItem(ModItems.CORN_KERNELS.get());
		this.basicItem(ModItems.DRIED_CORN_KERNELS.get());

		this.basicItem(ModItems.POPCORN_BAG.get());
		this.basicItem(ModItems.POPCORN.get());
		this.basicItem(ModItems.CHEESY_POPCORN.get());
		this.basicItem(ModItems.CARAMEL_POPCORN.get());
		this.basicItem(ModItems.MAPLE_POPCORN.get());

		this.basicItem(ModItems.MOUSE.get());
		this.basicItem(ModItems.COOKED_MOUSE.get());

		this.basicItem(ModItems.FOOD_SCRAPS.get());

		this.basicItem(ModItems.MAPLE_SAP_BOTTLE.get());
		this.basicItem(ModItems.MAPLE_SYRUP.get());
	}

	public ItemModelBuilder basicItem(Item item, ResourceLocation texture)
	{
		return getBuilder(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).toString())
				.parent(new ModelFile.UncheckedModelFile("item/generated"))
				.texture("layer0", texture);
	}
}
