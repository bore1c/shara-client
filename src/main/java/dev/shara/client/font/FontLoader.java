package dev.shara.client.font;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * FontLoader: simple manager that lists installed font files under config/shara-client/fonts
 * and provides safe operations for installing and enumerating fonts. Actual integration
 * with Minecraft text rendering is left to the FontManager; this class avoids direct font
 * registration to prevent mapping issues across Yarn versions.
 */
public final class FontLoader {
    private final Path fontDir = Path.of("config", "shara-client", "fonts");

    public FontLoader() {
        try { Files.createDirectories(fontDir); } catch (Exception ignored) {}
    }

    public List<Path> listFonts() {
        List<Path> out = new ArrayList<>();
        try {
            if (Files.exists(fontDir)) Files.list(fontDir).filter(p -> {
                String n = p.getFileName().toString().toLowerCase();
                return n.endsWith(".ttf") || n.endsWith(".otf");
            }).forEach(out::add);
        } catch (Exception ignored) {}
        return out;
    }

    public boolean install(Path source) {
        try {
            Path tgt = fontDir.resolve(source.getFileName());
            Files.copy(source, tgt);
            return true;
        } catch (Exception e) { return false; }
    }
}
