package it.unibo.mvc.impl;

import java.nio.file.Path;

public class ConfigurationParser {
    private final static Path PATH = Path.of("src/main/resources/config.yml");

    private static final int DEFAULT_MIN = 0;
    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_ATTEMPTS = 10;
    /**
     * @return a configuration parsed from file, or the default one if the file is
     *         not found or is not valid
     */
    public static Configuration parseConfiguration() {
        try {
            final var lines = java.nio.file.Files.readAllLines(PATH);
            int min = DEFAULT_MIN;
            int max = DEFAULT_MAX;
            int attempts = DEFAULT_ATTEMPTS;
            for (final var line : lines) {
                final var parts = line.split(":");
                if (parts.length != 2) {
                    continue;
                }
                final var key = parts[0].trim();
                final var value = parts[1].trim();
                switch (key) {
                    case "minimum" -> min = Integer.parseInt(value);
                    case "maximum" -> max = Integer.parseInt(value);
                    case "attempts" -> attempts = Integer.parseInt(value);
                    default -> {
                        System.out.println("Unknown configuration key: " + key);
                    }
                }
            }
            final var config = new Configuration.Builder()
                    .setMin(min)
                    .setMax(max)
                    .setAttempts(attempts)
                    .build();
            return config;
        } catch (java.io.IOException | IllegalArgumentException e) {
            System.out.println("Using default configuration due to error: " + e.getMessage());
            return new Configuration.Builder()
                    .setMin(DEFAULT_MIN)
                    .setMax(DEFAULT_MAX)
                    .setAttempts(DEFAULT_ATTEMPTS)
                    .build();
        }
    }
}
