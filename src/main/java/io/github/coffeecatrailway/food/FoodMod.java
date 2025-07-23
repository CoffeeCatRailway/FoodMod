package io.github.coffeecatrailway.food;

import com.mojang.logging.LogUtils;
import io.github.coffeecatrailway.food.client.extensions.common.FoodComboStackedExtension;
import io.github.coffeecatrailway.food.client.extensions.common.PizzaExtension;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.common.block.entity.ModBlockEntities;
import io.github.coffeecatrailway.food.common.item.ModCreativeTabs;
import io.github.coffeecatrailway.food.common.item.ModItems;
import io.github.coffeecatrailway.food.common.item.component.ModComponents;
import io.github.coffeecatrailway.food.common.item.crafting.ModRecipes;
import io.github.coffeecatrailway.food.config.FoodConfigs;
import io.github.coffeecatrailway.food.datagen.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(FoodMod.MODID)
public class FoodMod
{
	public static final String MODID = "ccfood";
	private static final Logger LOGGER = LogUtils.getLogger();

	public FoodMod(IEventBus modEventBus, ModContainer modContainer)
	{
		LOGGER.debug("Loading");

		ModComponents.init(modEventBus);
		ModBlocks.init(modEventBus);
		ModItems.init(modEventBus);
		ModRecipes.init(modEventBus);
		ModCreativeTabs.init(modEventBus);
		ModBlockEntities.init(modEventBus);

		modEventBus.addListener(this::clientExt);
		modEventBus.addListener(this::onGatherData);

		FoodConfigs.init(modContainer);
	}

	public void clientExt(RegisterClientExtensionsEvent event)
	{
		event.registerItem(new PizzaExtension(), ModItems.PIZZA);
		event.registerItem(new FoodComboStackedExtension(), ModItems.CRACKER);
		event.registerItem(new FoodComboStackedExtension(), ModItems.SANDWICH);
	}

	public void onGatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(event.includeClient(), new BlockModelGenerator(output, existingFileHelper));
		generator.addProvider(event.includeClient(), new ItemModelGenerator(output, existingFileHelper));
		generator.addProvider(event.includeClient(), new LanguageGenerator(output));
		generator.addProvider(event.includeServer(), new RecipeGenerator(output, lookupProvider));
		ModBlockTags blockTags = new ModBlockTags(output, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new ModItemTags(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
		generator.addProvider(event.includeServer(), new LootTableGenerator(output, lookupProvider));
	}

	public static ResourceLocation id(String location)
	{
		return ResourceLocation.fromNamespaceAndPath(MODID, location);
	}
}
