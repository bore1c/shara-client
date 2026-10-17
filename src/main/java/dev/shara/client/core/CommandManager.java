package dev.shara.client.core;

import dev.shara.client.command.ClientCommandManager;

public class CommandManager {
    private final ClientCommandManager clientCommandManager = new ClientCommandManager();

    public void registerDefaultCommands() {
        clientCommandManager.registerHelp();
    }

    public ClientCommandManager getClientCommandManager() {
        return clientCommandManager;
    }
}
