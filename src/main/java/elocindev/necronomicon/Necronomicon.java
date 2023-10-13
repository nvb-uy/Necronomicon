package elocindev.necronomicon;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Necronomicon implements ModInitializer {
	public static final String MODID = "necronomicon";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		LOGGER.info("Necronomicon Initialized");
	}
}
