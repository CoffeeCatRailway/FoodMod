package io.github.coffeecatrailway.food.config;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

public final class FoodConfigs
{
	private static final Logger LOGGER = LogUtils.getLogger();

	public static final FoodConfigClient CLIENT;
	public static final ModConfigSpec CLIENT_CONFIG_SPEC;

	public static final FoodConfigServer SERVER;
	public static final ModConfigSpec SERVER_CONFIG_SPEC;

	static
	{
		final Pair<FoodConfigClient, ModConfigSpec> client = new ModConfigSpec.Builder().configure(FoodConfigClient::new);
		final Pair<FoodConfigServer, ModConfigSpec> server = new ModConfigSpec.Builder().configure(FoodConfigServer::new);

		CLIENT_CONFIG_SPEC = client.getRight();
		CLIENT = client.getLeft();

		SERVER_CONFIG_SPEC = server.getRight();
		SERVER = server.getLeft();
	}

	public static void init(ModContainer modContainer)
	{
		modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

		LOGGER.info("Configurating...");
		modContainer.registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG_SPEC);
		modContainer.registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG_SPEC);
	}
}
