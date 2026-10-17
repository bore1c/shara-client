package dev.shara.client.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple manager for resources that need explicit cleanup (shaders, buffers).
 * Currently keeps track of named resources and invokes cleanup callbacks if provided.
 */
public class RenderResourceManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("SharaRenderRes");

    public static class ResourceEntry {
        public final String id;
        public final Runnable cleanup;
        public ResourceEntry(String id, Runnable cleanup) { this.id = id; this.cleanup = cleanup; }
    }

    private final List<ResourceEntry> resources = new ArrayList<>();

    public void register(String id, Runnable cleanup) {
        resources.add(new ResourceEntry(id, cleanup));
    }

    public void cleanupAll() {
        for (ResourceEntry e : resources) {
            try {
                if (e.cleanup != null) e.cleanup.run();
            } catch (Throwable t) {
                LOGGER.warn("Failed to cleanup resource {}", e.id, t);
            }
        }
        resources.clear();
    }
}
