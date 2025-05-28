package io.github.coffeecatrailway.food;

import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod(FoodMod.MODID)
public class FoodMod
{
    public static final String MODID = "ccfood";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FoodMod(IEventBus modEventBus, ModContainer modContainer)
    {
		LOGGER.info("Loading");

		ModItems.init(modEventBus);

		modEventBus.addListener(this::onGatherData);

        modContainer.registerConfig(net.neoforged.fml.config.ModConfig.Type.COMMON, ModConfig.SPEC);
    }

	public void onGatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(event.includeClient(), new DataGen.ItemModel(output, existingFileHelper));
		generator.addProvider(event.includeClient(), new DataGen.Language(output));
		generator.addProvider(event.includeServer(), new DataGen.Recipe(output, lookupProvider));
		DataGen.BlockTag blockTag = new DataGen.BlockTag(output, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTag);
		generator.addProvider(event.includeServer(), new DataGen.ItemTag(output, lookupProvider, blockTag.contentsGetter(), existingFileHelper));
	}

	public static ResourceLocation id(String location)
	{
		return ResourceLocation.fromNamespaceAndPath(MODID, location);
	}
}
