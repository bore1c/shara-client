package dev.shara.client.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class JsonUtils {
    private JsonUtils() {}

    public static JsonElement readJson(Path path, Gson gson) throws Exception {
        return gson.fromJson(Files.readString(path, StandardCharsets.UTF_8), JsonElement.class);
    }
}
