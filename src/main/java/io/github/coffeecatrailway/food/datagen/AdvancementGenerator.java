package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.advancements.ChoppingBoardTrigger;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.advancements.critereon.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2025
 */
public class AdvancementGenerator extends AdvancementProvider
{
	public AdvancementGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper)
	{
		super(output, registries, existingFileHelper, List.of(io.github.coffeecatrailway.food.datagen.AdvancementGenerator::generate));
	}

	@SuppressWarnings("unused")
	private static void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper)
	{
		AdvancementHolder root = advancement("root", "Coffee's Fun Food's", "Have fun cooking!", ModItems.CHEESE_SLICE.get(), FoodMod.id("textures/gui/advancements/backgrounds/cheese.png"), AdvancementType.TASK, false, true, false,
				builder -> builder.addCriterion("always", PlayerTrigger.TriggerInstance.tick()), saver, existingFileHelper);

		AdvancementHolder rise = advancement("rise", "Rise", "Craft Flour", ModItems.FLOUR, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(root).addCriterion("has_flour", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.FLOUR)), saver, existingFileHelper);
		AdvancementHolder salty = advancement("salty", "Salty", "Craft Salt", ModItems.SALT, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(root).addCriterion("has_salt", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SALT)), saver, existingFileHelper);

		AdvancementHolder rolling = advancement("rolling", "Rolling", "Craft a Rolling Pin", ModItems.ROLLING_PIN_WOODEN, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(root).addCriterion("has_rolling_pin", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.TOOLS_ROLLING_PIN))), saver, existingFileHelper);
		AdvancementHolder crackCrack = advancement("crack_crack", "Crack Crack", "Craft an Unbaked Cracker", ModItems.UNBAKED_CRACKER, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(rolling).addCriterion("has_cracker", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.UNBAKED_CRACKER)), saver, existingFileHelper);
		AdvancementHolder chopChop = advancement("chop_chop", "Chop Chop", "Use a Chopping Board", ModBlocks.OAK_CHOPPING_BOARD, null, AdvancementType.GOAL, true, true, false,
				builder -> builder.parent(rolling).addCriterion("use_chopping_board", ChoppingBoardTrigger.TriggerInstance.use()), saver, existingFileHelper);

		AdvancementHolder gears = advancement("gears", "Gears", "Craft a Gear", ModItems.GEAR_WOODEN, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(root).addCriterion("has_gear", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.GEARS))), saver, existingFileHelper);
		AdvancementHolder curdler = advancement("curdler", "Curdler", "Craft a Cheese Curdler", ModItems.CURDLER_WOODEN, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(gears).addCriterion("has_curdler", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.TOOLS_CURDLER))), saver, existingFileHelper);
		// TODO: cheese advancements

		AdvancementHolder woodChop = advancement("wood_chop", "Wood Chop", "Craft a wooden knife", ModItems.KNIFE_WOODEN, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(root).addCriterion("has_wooded_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_WOODEN))), saver, existingFileHelper);
		AdvancementHolder crudeChop = advancement("crude_chop", "Crude Chop", "Craft a stone knife", ModItems.KNIFE_STONE, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(woodChop).addCriterion("has_stone_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_STONE))), saver, existingFileHelper);
		AdvancementHolder staticChop = advancement("static_chop", "Static Chop", "Craft a copper knife", ModItems.KNIFE_COPPER, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(crudeChop).addCriterion("has_copper_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_COPPER))), saver, existingFileHelper);
		AdvancementHolder shinyChop = advancement("shiny_chop", "Shiny Chop", "Craft a gold knife", ModItems.KNIFE_GOLD, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(staticChop).addCriterion("has_gold_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_GOLD))), saver, existingFileHelper);
		AdvancementHolder sturdyChop = advancement("sturdy_chop", "Sturdy Chop", "Craft an iron knife", ModItems.KNIFE_IRON, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(shinyChop).addCriterion("has_iron_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_IRON))), saver, existingFileHelper);
		AdvancementHolder fancyChop = advancement("fancy_chop", "Fancy Chop", "Craft a diamond knife", ModItems.KNIFE_DIAMOND, null, AdvancementType.GOAL, true, true, false,
				builder -> builder.parent(sturdyChop).addCriterion("has_diamond_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_DIAMOND))), saver, existingFileHelper);
		AdvancementHolder hotChop = advancement("hot_chop", "Crude Chop", "Craft a netherite knife", ModItems.KNIFE_NETHERITE, null, AdvancementType.GOAL, true, true, false,
				builder -> builder.parent(fancyChop).addCriterion("has_netherite_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.KNIFE_NETHERITE))), saver, existingFileHelper);

		AdvancementHolder hamSlice = advancement("ham_slice", "Ham Slice", "Cut a slice of ham", ModItems.HAM_SLICE, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(woodChop).addCriterion("has_ham_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.FOODS_HAM))), saver, existingFileHelper);
		AdvancementHolder sizzle = advancement("sizzle", "Sizzle", "Cut strips of bacon", ModItems.RAW_BACON, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(hamSlice).addCriterion("has_cooked_ham_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.FOODS_BACON))), saver, existingFileHelper);

		AdvancementHolder cheeseSlice = advancement("cheese_slice", "Cheese Slice", "Cut a slice of cheese", ModItems.CHEESE_SLICE, null, AdvancementType.TASK, true, true, false,
				builder -> builder.parent(woodChop).addCriterion("has_cheese_slice", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.FOODS_CHEESE_SLICE))), saver, existingFileHelper);

		// TODO: rat advancements
		// TODO: popcorn advancements
		// TODO: grill advancements
		// TODO: pizza oven advancements
	}

	private static AdvancementHolder advancement(String id, String title, String description, ItemLike icon, @Nullable ResourceLocation background, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden, Function<Advancement.Builder, Advancement.Builder> factory, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper)
	{
		String titlePath = "advancement.%s.%s.title".formatted(FoodMod.MODID, id);
		String descriptionPath = "advancement.%s.%s.description".formatted(FoodMod.MODID, id);

		Optional<ResourceLocation> backgroundOptional = background == null ? Optional.empty() : Optional.of(background);
		DisplayInfo displayInfo = new DisplayInfo(new ItemStack(icon), Component.translatable(titlePath), Component.translatable(descriptionPath), backgroundOptional, type, showToast, announceChat, hidden);

		AdvancementHolder advancement = factory.apply(Advancement.Builder.advancement().display(displayInfo)).save(saver, FoodMod.id(id), existingFileHelper);
		LanguageGenerator.ADVANCEMENTS.put(titlePath, title);
		LanguageGenerator.ADVANCEMENTS.put(descriptionPath, description);
		return advancement;
	}
}
