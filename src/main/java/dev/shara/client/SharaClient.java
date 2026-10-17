package dev.shara.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.shara.client.command.Command;
import dev.shara.client.command.ClientCommandManager;
import dev.shara.client.core.*;
import dev.shara.client.input.InputHandler;
import dev.shara.client.input.KeybindManager;
import dev.shara.client.module.modules.*;
import dev.shara.client.ui.clickgui.SharaClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharaClient implements ClientModInitializer {
    public static final String MOD_ID = "shara-client";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static SharaClient instance;

    private ModuleManager moduleManager;
    private SettingManager settingManager;
    private ConfigManager configManager;
    private ThemeManager themeManager;
    private FontManager fontManager;
    private AnimationManager animationManager;
    private RenderManager renderManager;
    private dev.shara.client.core.ParticleManager particleManager;
    private HudManager hudManager;
    private CommandManager commandManager;
    private ProfileManager profileManager;
    private PerformanceMonitor performanceMonitor;
    private KeybindManager keybindManager;

    public static SharaClient get() {
        return instance;
    }

    @Override
    public void onInitializeClient() {
        instance = this;
        LOGGER.info("Initializing SHARA Client...");

        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        themeManager = new ThemeManager();
        fontManager = new FontManager();
        animationManager = new AnimationManager();
        renderManager = new RenderManager();
        particleManager = new dev.shara.client.core.ParticleManager();
        hudManager = new HudManager();
        commandManager = new CommandManager();
        profileManager = new ProfileManager();
        performanceMonitor = new PerformanceMonitor();
        keybindManager = new KeybindManager();

        // Register core modules
        moduleManager.registerModule(new WatermarkModule());
        moduleManager.registerModule(new FpsModule());
        moduleManager.registerModule(new CoordinatesModule());
        moduleManager.registerModule(new PingModule());
        moduleManager.registerModule(new TargetInfoModule());
        moduleManager.registerModule(new ItemEspModule());

        // Register commands
        ClientCommandManager cm = commandManager.getClientCommandManager();
        cm.registerHelp();
        cm.register(new Command("gui") {
            @Override
            public void execute(String[] args) {
                MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new SharaClickGuiScreen()));
            }
        });

        // Input/keybind initialization
        InputHandler.init(keybindManager);

        // tick listener for perf monitor
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                performanceMonitor.tick();
                // particle tick with delta estimated from FPS
                double delta = 1.0 / Math.max(20.0, performanceMonitor.getFps());
                particleManager.tick(delta);
            } catch (Throwable t) {
                LOGGER.debug("Tick exception", t);
            }
        });

        try {
            configManager.load();
        } catch (Exception e) {
            LOGGER.warn("Failed to load SHARA config", e);
        }

        LOGGER.info("SHARA Client initialized.");
    }

    public ModuleManager getModuleManager() { return moduleManager; }
    public SettingManager getSettingManager() { return settingManager; }
    public ConfigManager getConfigManager() { return configManager; }
    public ThemeManager getThemeManager() { return themeManager; }
    public FontManager getFontManager() { return fontManager; }
    public AnimationManager getAnimationManager() { return animationManager; }
    public RenderManager getRenderManager() { return renderManager; }
    public dev.shara.client.core.ParticleManager getParticleManager() { return particleManager; }
    public HudManager getHudManager() { return hudManager; }
    public CommandManager getCommandManager() { return commandManager; }
    public ProfileManager getProfileManager() { return profileManager; }
    public PerformanceMonitor getPerformanceMonitor() { return performanceMonitor; }
    public KeybindManager getKeybindManager() { return keybindManager; }
}
