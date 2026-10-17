package dev.shara.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.shara.client.core.CommandManager;
import dev.shara.client.core.ConfigManager;
import dev.shara.client.core.FontManager;
import dev.shara.client.core.HudManager;
import dev.shara.client.core.ModuleManager;
import dev.shara.client.core.PerformanceMonitor;
import dev.shara.client.core.ProfileManager;
import dev.shara.client.core.RenderManager;
import dev.shara.client.core.SettingManager;
import dev.shara.client.core.ThemeManager;
import dev.shara.client.core.AnimationManager;
import dev.shara.client.core.ParticleManager;
import dev.shara.client.module.modules.FpsModule;
import dev.shara.client.module.modules.WatermarkModule;
import net.fabricmc.api.ClientModInitializer;
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
    private ParticleManager particleManager;
    private HudManager hudManager;
    private CommandManager commandManager;
    private ProfileManager profileManager;
    private PerformanceMonitor performanceMonitor;

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
        particleManager = new ParticleManager();
        hudManager = new HudManager();
        commandManager = new CommandManager();
        profileManager = new ProfileManager();
        performanceMonitor = new PerformanceMonitor();

        moduleManager.registerModule(new WatermarkModule());
        moduleManager.registerModule(new FpsModule());

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
    public ParticleManager getParticleManager() { return particleManager; }
    public HudManager getHudManager() { return hudManager; }
    public CommandManager getCommandManager() { return commandManager; }
    public ProfileManager getProfileManager() { return profileManager; }
    public PerformanceMonitor getPerformanceMonitor() { return performanceMonitor; }
}
