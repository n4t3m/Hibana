package com.n4t3m.hibana;

import com.n4t3m.hibana.event.EventManager;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hibana implements ModInitializer {
	public static final String MOD_ID = "hibana";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier BOAT_FLY_ID = new Identifier("hibana:boat_fly_enabled");
	public static SoundEvent BOAT_FLY_ENABLED_EVENT = SoundEvent.of(BOAT_FLY_ID);
	public static final Identifier BOAT_FLY_DISABLE_ID = new Identifier("hibana:boat_fly_disabled");
	public static SoundEvent BOAT_FLY_DISABLED_EVENT = SoundEvent.of(BOAT_FLY_DISABLE_ID);
	public static final Identifier XRAY_ENABLED_IDENTIFIER = new Identifier("hibana:xray_enabled");
	public static SoundEvent XRAY_ENABLED_SOUND_EVENT = SoundEvent.of(XRAY_ENABLED_IDENTIFIER);
	public static final Identifier XRAY_DISABLED_IDENTIFIER = new Identifier("hibana:xray_disabled");
	public static SoundEvent XRAY_DISABLED_SOUND_EVENT = SoundEvent.of(XRAY_DISABLED_IDENTIFIER);
	public static final Identifier PLAYER_FLY_ENABLED_IDENTIFIER = new Identifier("hibana:player_fly_enabled");
	public static SoundEvent PLAYER_FLY_ENABLED_SOUND_EVENT = SoundEvent.of(PLAYER_FLY_ENABLED_IDENTIFIER);
	public static final Identifier PLAYER_FLY_DISABLED_IDENTIFIER = new Identifier("hibana:player_fly_disabled");
	public static SoundEvent PLAYER_FLY_DISABLED_SOUND_EVENT = SoundEvent.of(PLAYER_FLY_DISABLED_IDENTIFIER);
	public static final Identifier FULLBRIGHT_ENABLED_IDENTIFIER = new Identifier("hibana:fullbright_enabled");
	public static SoundEvent FULLBRIGHT_ENABLED_SOUND_EVENT = SoundEvent.of(FULLBRIGHT_ENABLED_IDENTIFIER);
	// todo (natem135): Add proper sound once recorded and available.
	public static final Identifier FULLBRIGHT_DISABLED_IDENTIFIER = new Identifier("hibana:fullbright_disabled");
	public static SoundEvent FULLBRIGHT_DISABLED_SOUND_EVENT = SoundEvent.of(FULLBRIGHT_DISABLED_IDENTIFIER);

	private static final EventManager eventManager = new EventManager();

	public static EventManager getEventManager() {
		return eventManager;
	}

	@Override
	public void onInitialize() {
		Registry.register(Registries.SOUND_EVENT, BOAT_FLY_ID, BOAT_FLY_ENABLED_EVENT);
		Registry.register(Registries.SOUND_EVENT, BOAT_FLY_DISABLE_ID, BOAT_FLY_DISABLED_EVENT);

		LOGGER.info("Initializing Mod.");
	}
}