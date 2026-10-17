package dev.shara.client.core;

import dev.shara.client.module.Module;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void registerModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Optional<Module> getModuleById(String id) {
        return modules.stream()
                .filter(module -> module.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void toggleModule(String id) {
        getModuleById(id).ifPresent(Module::toggle);
    }

    public void enableModule(String id) {
        getModuleById(id).ifPresent(Module::enable);
    }

    public void disableModule(String id) {
        getModuleById(id).ifPresent(Module::disable);
    }
}
