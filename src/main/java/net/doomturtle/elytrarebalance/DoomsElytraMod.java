package net.doomturtle.elytrarebalance;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.entity.player.PlayerEntity;

import java.io.File;
import java.io.IOException;



public class DoomsElytraMod implements ModInitializer {

	public static final String MOD_ID = "dooms-elytra-rebalance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static float elytra_speed_config = 1.0f;
	public static int elytra_durability_config = 432;

	@Override
	public void onInitialize()
	{
		LOGGER.info("Sir Doom Turtle's Elytra Rebalance Mod Initialized");

		ModConfig.init();
		elytra_speed_config = Math.max(0.0f, Math.min(ModConfig.elytra_speed_multiplier, 1.0f));
		elytra_durability_config = Math.max(1, Math.min(ModConfig.elytra_durability, 9999));
		

		File configFile = new File("config/dooms-elytra-rebalance.toml");
		if (configFile.exists())
		{
			try
			{
				ConfigUtils.injectCommentsIntoConfigFile(configFile);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}


		LOGGER.info("Elytra Speed Multiplier set to " + ModConfig.elytra_speed_multiplier );
		LOGGER.info("Elytra Durability set to " + ModConfig.elytra_durability );

		ServerTickEvents.START_SERVER_TICK.register(
				server -> {
					for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
						if (player.isFallFlying()) {
							reduceElytraSpeed(player);
						}}
				}
		);




	}


	/*
		This function is run on every player who is currently FallFlying. It checks if the Player is flying in a
		horizontal-ish direction AND if they are exceeding the speed threshold that is influenced by the
		elytraSpeedMultiplier variable in the config file.
		If this is the case, the player's velocity is smoothly reduced back down until they no longer exceed the
		threshold. This way, players can still fly crazy fast if they dive-bomb in their elytras, but they
		will be slowed down if they try to pull out of the dive, which makes the whole flying experience a bit
		smoother

		@param player = the player
		@returns = void
	 */
	private void reduceElytraSpeed(PlayerEntity player)
	{
		float elytra_speed_multiplier = elytra_speed_config;
		if (elytra_speed_multiplier == 1.0f)
		{
			return;
		}

		Vec3d velocity = player.getVelocity();
		double velocity_length = velocity.length();

		float max_horizontal_speed = 1.66f;
		double smoothing_factor = 0.3;
		double dynamic_threshold = elytra_speed_multiplier * ((1-smoothing_factor) * max_horizontal_speed);


		boolean angle_check = playerFlyingHorizontal(velocity);

		if (velocity_length > dynamic_threshold && angle_check)
		{
			Vec3d target_velocity = velocity.normalize().multiply(elytra_speed_multiplier * velocity_length);
			Vec3d velocity_diff = velocity.subtract(target_velocity);

			Vec3d new_velocity = velocity.subtract(velocity_diff.multiply(smoothing_factor));

			player.setVelocity(new_velocity);
			player.velocityModified = true;
			player.sendAbilitiesUpdate();
		}
	}



	/*
		This function checks to see if the player is flying in a horizontal-ish direction by checking their
		elevation angle. If their elevation angle is between -50 and 50 radians, then they are considered to
		be flying horizontally-ish and the elytra speed penalty should be applied.

		@param velocity = the player's current velocity
		@returns = true if player's elevation is between -50 and 50 radians, false otherwise
	 */
	private static boolean playerFlyingHorizontal(Vec3d velocity)
	{
		double max_elevation_angle = Math.toRadians(50);
		double min_elevation_angle = -Math.toRadians(50);
		double vertical_velocity = velocity.y;
		double horizontal_speed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
		double elevation_angle = Math.atan2(vertical_velocity, horizontal_speed);
		return (elevation_angle >= min_elevation_angle && elevation_angle <= max_elevation_angle);
	}

}