package dev.shara.client.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FontManager {
    private final Path fontDir = Path.of("config", "shara-client", "fonts");

    public FontManager() {
        try {
            Files.createDirectories(fontDir);
        } catch (IOException ignored) {
        }
    }

    public Path getFontDir() {
        return fontDir;
    }
}
