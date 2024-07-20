package net.doomturtle.elytrarebalance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ConfigUtils {

    public static void injectCommentsIntoConfigFile(File configFile) throws IOException {
        Path path = configFile.toPath();
        List<String> lines = Files.readAllLines(path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line : lines) {
                if (line.trim().startsWith("elytra_speed_multiplier")) {
                    writer.write("\n# This multiplier affects the speed of the Elytra.\n");
                    writer.write("# Setting this to 1.0 will use the default speed without modification.\n");
                    writer.write("# Accepted values from 0.0 to 1.0\n");
                } else if (line.trim().startsWith("elytra_durability")) {
                    writer.write("\n# This value sets the custom durability of the Elytra.\n");
                    writer.write("# Accepted values from 1 to 9999\n");
                }
                writer.write(line);
                writer.newLine();
            }
        }
    }
}