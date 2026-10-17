package dev.shara.client.command;

import dev.shara.client.SharaClient;
import java.util.ArrayList;
import java.util.List;

public class ClientCommandManager {
    private final List<Command> commands = new ArrayList<>();

    public void register(Command command) {
        commands.add(command);
    }

    public void registerHelp() {
        register(new Command("help") {
            @Override
            public void execute(String[] args) {
                SharaClient.LOGGER.info("Available commands: .help, .modules, .toggle <module>");
            }
        });
    }

    public void execute(String input) {
        if (input == null || input.isBlank()) {
            return;
        }
        String[] split = input.trim().split("\\s+");
        String commandName = split[0];
        String[] args = java.util.Arrays.copyOfRange(split, 1, split.length);

        if (commandName.equalsIgnoreCase(".help")) {
            SharaClient.LOGGER.info("Available commands: .help, .modules, .toggle <module>");
            return;
        }

        if (commandName.equalsIgnoreCase(".modules")) {
            SharaClient.get().getModuleManager().getModules().forEach(module ->
                    SharaClient.LOGGER.info(module.getName() + " [" + (module.isEnabled() ? "enabled" : "disabled") + "]"));
            return;
        }

        if (commandName.equalsIgnoreCase(".toggle") && args.length > 0) {
            SharaClient.get().getModuleManager().toggleModule(args[0]);
        }
    }
}
