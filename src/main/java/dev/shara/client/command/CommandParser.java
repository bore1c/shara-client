package dev.shara.client.command;

import dev.shara.client.SharaClient;

public final class CommandParser {
    private CommandParser() {}

    public static void run(String input) {
        if (input == null) return;
        String trimmed = input.trim();
        if (trimmed.startsWith(".")) trimmed = trimmed.substring(1);
        SharaClient.get().getCommandManager().getClientCommandManager().execute("." + trimmed);
    }
}
