package net.doomturtle.elytrarebalance;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = "dooms-elytra-rebalance")
public class ModConfig implements ConfigData {
    public float elytraSpeedMultiplier = 0.7f; // Default value

    public static void init()
    {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }


}