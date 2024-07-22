package net.doomturtle.elytrarebalance;

import java.io.*;

public class ModConfig {

    private static final String CONFIG_FOLDER_PATH = "config";
    private static final String CONFIG_FILE_PATH = "config/dooms-elytra-rebalance.toml";
    private double elytra_speed_modifier;
    private int elytra_durability;

    public ModConfig()
    {

        File configFolder = new File(CONFIG_FOLDER_PATH);
        if (!configFolder.exists())
        {
            configFolder.mkdirs();
        }

        File configFile = new File(CONFIG_FILE_PATH);

        if (configFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("elytra_speed_modifier"))
                    {
                        elytra_speed_modifier = parseDouble(line);
                    }
                    else if (line.trim().startsWith("elytra_durability"))
                    {
                        elytra_durability = parseInt(line);
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            System.out.println("Config file does not exist.");

            elytra_speed_modifier = 0.8;
            elytra_durability = 800;
            createDefaultConfigFile(configFile);
        }
    }

    private void createDefaultConfigFile(File configFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
            writer.write("# Configuration file for Sir Doom Turtle`s Elytra Rebalance\n\n");
            writer.write("# This multiplier affects the speed of the Elytra.\n");
            writer.write("# Setting this to 1.0 will use the default speed without modification.\n");
            writer.write("# Accepted values from 0.0 to 1.0\n");
            writer.write("# Vanilla Minecraft Default is 1.0\n");
            writer.write("elytra_speed_modifier = " + elytra_speed_modifier + "\n");
            writer.write("\n# This value sets the custom durability of the Elytra.\n");
            writer.write("# Accepted values from 1 to 9999\n");
            writer.write("# Vanilla Minecraft Default is 432\n");
            writer.write("elytra_durability = " + elytra_durability + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double parseDouble(String line) {
        return Double.parseDouble(line.split("=")[1].trim());
    }

    private int parseInt(String line) {
        return Integer.parseInt(line.split("=")[1].trim());
    }

    public double getElytraSpeedModifier() {
        return elytra_speed_modifier;
    }

    public int getElytraDurability() {
        return elytra_durability;
    }
}