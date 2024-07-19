package net.doomturtle.elytrarebalance;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigUtils {

    public static void injectCommentsIntoConfigFile(File file) throws IOException {
        // Read the existing content
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        // Add comments to the beginning
        String comments = "# Elytra Rebalance Configuration File\n\n" +
                "# General settings\n" +
                "[general]\n" +
                "# This multiplier affects the speed of the Elytra.\n" +
                "# Setting this to 1.0 will use the default speed without modification.\n" +
                "# Values between 0.0 and 1.0 will reduce the speed.\n";

        // Write the comments and existing content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(comments);
            writer.write(content.toString());
        }
    }
}