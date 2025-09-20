package io.github.coffeecatrailway.food.common.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.coffeecatrailway.food.FoodMod;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2025
 */
public class ChoppingBoardTrigger extends SimpleCriterionTrigger<ChoppingBoardTrigger.TriggerInstance>
{
	@Override
	public Codec<ChoppingBoardTrigger.TriggerInstance> codec()
	{
		return TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player)
	{
		this.trigger(player, triggerInstance -> true);
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance
	{
		public static final Codec<ChoppingBoardTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance ->
				instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(ChoppingBoardTrigger.TriggerInstance::player))
						.apply(instance, TriggerInstance::new));

		public static Criterion<ChoppingBoardTrigger.TriggerInstance> use()
		{
			return ModCriteriaTriggers.CHOPPING_BOARD.get().createCriterion(new TriggerInstance(Optional.empty()));
		}
	}
}
