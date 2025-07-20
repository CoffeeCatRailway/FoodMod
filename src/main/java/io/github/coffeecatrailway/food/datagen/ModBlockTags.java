package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class ModBlockTags extends BlockTagsProvider
{
	public static final TagKey<Block> MOUSE_SEARCHABLE = tag("mouse_searchable");

	public static final TagKey<Block> MINEABLE_WITH_KNIFE = tag("mineable/knife");

	public static final TagKey<Block> CHOPPING_BOARDS = tag("chopping_boards");

	public static final TagKey<Block> MAPLE_LOGS = tagCommon("maple_logs");

	public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, FoodMod.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider)
	{
		this.tag(MOUSE_SEARCHABLE).addTags(Tags.Blocks.BARRELS, Tags.Blocks.CHESTS, BlockTags.SHULKER_BOXES).add(Blocks.DISPENSER, Blocks.DROPPER, Blocks.HOPPER);

		this.tag(MINEABLE_WITH_KNIFE);// cheese blocks

		this.tag(CHOPPING_BOARDS);// leaves

		this.tag(BlockTags.CROPS).add(ModBlocks.PINEAPPLE_PLANT.get(), ModBlocks.TOMATO_PLANT.get(), ModBlocks.CORN_PLANT.get());

		this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(CHOPPING_BOARDS);
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE);// grill, pizza oven, popcorn machine, tree tap

		this.tag(BlockTags.PLANKS);// maple
		this.tag(BlockTags.WOODEN_BUTTONS);
		this.tag(BlockTags.WOODEN_DOORS);
		this.tag(BlockTags.WOODEN_STAIRS);
		this.tag(BlockTags.WOODEN_SLABS);
		this.tag(BlockTags.WOODEN_FENCES);
		this.tag(BlockTags.SAPLINGS);
		this.tag(MAPLE_LOGS);
		this.tag(BlockTags.LOGS).addTag(MAPLE_LOGS);
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(MAPLE_LOGS);
		this.tag(BlockTags.FLOWER_POTS);
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES);
		this.tag(BlockTags.LEAVES);
		this.tag(BlockTags.WOODEN_TRAPDOORS);
		this.tag(BlockTags.STANDING_SIGNS);
		this.tag(BlockTags.WALL_SIGNS);
		this.tag(BlockTags.CEILING_HANGING_SIGNS);
		this.tag(BlockTags.WALL_HANGING_SIGNS);
		this.tag(BlockTags.FENCE_GATES);
	}

	private static TagKey<Block> tag(String location)
	{
		return TagKey.create(Registries.BLOCK, FoodMod.id(location));
	}

	private static TagKey<Block> tagCommon(String location)
	{
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", location));
	}
}
