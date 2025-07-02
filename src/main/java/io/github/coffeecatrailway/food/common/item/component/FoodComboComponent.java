package io.github.coffeecatrailway.food.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * @author CoffeeCatRailway
 * Created: 30/05/2025
 */
public record FoodComboComponent(List<ItemStack> ingredients, boolean toasted)
{
	public static final FoodComboComponent EMPTY = new FoodComboComponent(List.of(), false);

	public static final Codec<FoodComboComponent> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					ItemStack.CODEC.listOf().fieldOf("ingredients").forGetter(FoodComboComponent::ingredients),
					Codec.BOOL.fieldOf("toasted").forGetter(FoodComboComponent::toasted)
			).apply(instance, FoodComboComponent::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, FoodComboComponent> STREAM_CODEC = StreamCodec.composite(
			ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()), FoodComboComponent::ingredients, ByteBufCodecs.BOOL, FoodComboComponent::toasted, FoodComboComponent::new
	);
}
