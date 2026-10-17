package dev.shara.client.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FontManagerV2 {
    private final Path fontDir = Path.of("config", "shara-client", "fonts");

    public FontManagerV2() {
        try {
            Files.createDirectories(fontDir);
        } catch (Exception ignored) {}
    }

    public List<Path> listFonts() {
        List<Path> out = new ArrayList<>();
        try {
            if (Files.exists(fontDir)) {
                Files.list(fontDir).filter(p -> {
                    String n = p.getFileName().toString().toLowerCase();
                    return n.endsWith(".ttf") || n.endsWith(".otf");
                }).forEach(out::add);
            }
        } catch (Exception ignored) {}
        return out;
    }

    public Path getFontDir() { return fontDir; }
}
