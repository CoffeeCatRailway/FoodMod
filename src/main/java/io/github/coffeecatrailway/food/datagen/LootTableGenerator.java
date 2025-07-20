package io.github.coffeecatrailway.food.datagen;

import io.github.coffeecatrailway.food.FoodMod;
import io.github.coffeecatrailway.food.common.block.CornPlantBlock;
import io.github.coffeecatrailway.food.common.block.ModBlocks;
import io.github.coffeecatrailway.food.common.block.PineapplePlantBlock;
import io.github.coffeecatrailway.food.common.block.TomatoPlantBlock;
import io.github.coffeecatrailway.food.common.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * @author CoffeeCatRailway
 * Created: 17/07/2025
 */
public class LootTableGenerator extends LootTableProvider
{
	public LootTableGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
	{
		super(output, Set.of(), List.of(
				new SubProviderEntry(ChestLoot::new, LootContextParamSets.CHEST),
				new SubProviderEntry(EntityLoot::new, LootContextParamSets.ENTITY),
				new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
		), lookupProvider);
	}

	private record ChestLoot(HolderLookup.Provider lookupProvider) implements LootTableSubProvider
	{

		@Override
		public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output)
		{

		}

		private LootPoolSingletonContainer.Builder<?> lootItem(ItemLike item)
		{
			return LootItem.lootTableItem(item);
		}

		private LootPoolSingletonContainer.Builder<?> lootItemWithCount(ItemLike item, int min, int max)
		{
			return LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
		}

		private void createTable(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output, String id, float minRolls, float maxRolls, LootPoolEntryContainer.Builder<?>... entriesBuilder)
		{
			LootPool.Builder pool = LootPool.lootPool().setRolls(UniformGenerator.between(minRolls, maxRolls));
			for (LootPoolEntryContainer.Builder<?> entry : entriesBuilder) pool.add(entry);
			output.accept(ResourceKey.create(Registries.LOOT_TABLE, FoodMod.id("chests/" + id)), LootTable.lootTable().withPool(pool));
		}
	}

	private static class EntityLoot extends EntityLootSubProvider
	{

		protected EntityLoot(HolderLookup.Provider lookupProvider)
		{
			super(FeatureFlags.REGISTRY.allFlags(), lookupProvider);
		}

		@Override
		public void generate()
		{

		}

		@Override
		protected Stream<EntityType<?>> getKnownEntityTypes()
		{
			return BuiltInRegistries.ENTITY_TYPE.stream().filter(entry -> BuiltInRegistries.ENTITY_TYPE.getKey(entry).getNamespace().equals(FoodMod.MODID));
		}
	}

	private static class BlockLoot extends BlockLootSubProvider
	{

		protected BlockLoot(HolderLookup.Provider lookupProvider)
		{
			super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
		}

		@Override
		protected void generate()
		{
			HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
			Holder.Reference<Enchantment> fortune = enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE);

			int pineappleMaxAge = ModBlocks.PINEAPPLE_PLANT.get().getMaxAge();
			this.add(ModBlocks.PINEAPPLE_PLANT.get(), block -> LootTable.lootTable()
					.withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(ModItems.PINEAPPLE.get())
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
											.setProperties(StatePropertiesPredicate.Builder.properties()
													.hasProperty(PineapplePlantBlock.AGE, pineappleMaxAge)))
									.otherwise(LootItem.lootTableItem(ModItems.PINEAPPLE_CROWN.get())))))
					.withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(UniformGenerator.between(0, 1))
							.add(LootItem.lootTableItem(ModItems.PINEAPPLE_CROWN.get())
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
											.setProperties(StatePropertiesPredicate.Builder.properties()
													.hasProperty(PineapplePlantBlock.AGE, pineappleMaxAge)))))));

			int tomatoMaxAge = ModBlocks.TOMATO_PLANT.get().getMaxAge();
			this.add(ModBlocks.TOMATO_PLANT.get(), block -> LootTable.lootTable()
					.withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(ModItems.TOMATO.get())
									.apply(ApplyBonusCount.addBonusBinomialDistributionCount(fortune, .65f, 3))
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
											.setProperties(StatePropertiesPredicate.Builder.properties()
													.hasProperty(TomatoPlantBlock.AGE, tomatoMaxAge)
													.hasProperty(TomatoPlantBlock.HALF, DoubleBlockHalf.LOWER)))
									.otherwise(LootItem.lootTableItem(ModItems.TOMATO_SEEDS.get())
											.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
													.setProperties(StatePropertiesPredicate.Builder.properties()
															.hasProperty(TomatoPlantBlock.HALF, DoubleBlockHalf.LOWER))))))));

			int cornMaxAge = ModBlocks.CORN_PLANT.get().getMaxAge();
			this.add(ModBlocks.CORN_PLANT.get(), block -> LootTable.lootTable()
					.withPool(this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(ModItems.CORN_COB.get())
									.apply(ApplyBonusCount.addBonusBinomialDistributionCount(fortune, .5714286f, 2))
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
											.setProperties(StatePropertiesPredicate.Builder.properties()
													.hasProperty(CornPlantBlock.AGE, cornMaxAge)
													.hasProperty(CornPlantBlock.HALF, DoubleBlockHalf.LOWER)))
									.otherwise(LootItem.lootTableItem(ModItems.CORN_COB.get())
											.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
													.setProperties(StatePropertiesPredicate.Builder.properties()
															.hasProperty(CornPlantBlock.HALF, DoubleBlockHalf.LOWER))))))));
		}

		@Override
		protected Iterable<Block> getKnownBlocks()
		{
			return BuiltInRegistries.BLOCK.stream().filter(entry -> BuiltInRegistries.BLOCK.getKey(entry).getNamespace().equals(FoodMod.MODID))::iterator;
		}
	}
}
