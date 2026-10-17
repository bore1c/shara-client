package dev.shara.client.module;

public abstract class BaseModule extends Module {
    protected BaseModule(String id, String name, String description, ModuleCategory category) {
        super(id, name, description, category);
    }
}
