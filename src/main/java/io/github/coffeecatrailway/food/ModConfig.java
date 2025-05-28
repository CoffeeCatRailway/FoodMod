package io.github.coffeecatrailway.food;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = FoodMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModConfig
{
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

	private static final ModConfigSpec.BooleanValue ALLOW_POTIONS_SPEC = BUILDER
			.comment("Whether sandwiches can have potions or not")
			.define("allowPotions", true);

	static final ModConfigSpec SPEC = BUILDER.build();

	public static boolean ALLOW_POTIONS;

	@SubscribeEvent
	static void onLoad(final ModConfigEvent event)
	{
		ALLOW_POTIONS = ALLOW_POTIONS_SPEC.get();
	}
}
