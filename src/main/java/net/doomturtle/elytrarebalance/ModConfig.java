package net.doomturtle.elytrarebalance;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = "dooms-elytra-rebalance")
public class ModConfig implements ConfigData {
    public static float elytra_speed_multiplier = 0.8f;
    public static int elytra_durability = 800;

    public static void init()
    {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
    }
    public static ModConfig get()
    {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }


}