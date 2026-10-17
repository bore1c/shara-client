package dev.shara.client;

/* CHANGELOG

Initial development commits:
- Scaffold (entrypoint, gradle, fabric.mod.json)
- Core managers: ModuleManager, SettingManager, ConfigManager, ThemeManager, FontManager, AnimationManager, RenderManager, ParticleManager, HudManager, CommandManager, ProfileManager, PerformanceMonitor
- Settings: basic and advanced types
- ClickGUI scaffold with controls and SettingsPanel
- HUD Editor (drag/resize snap-grid, export/import layouts)
- HUD modules: Watermark, FPS, Coordinates, Ping, TargetInfo, Item ESP, Entity List, Keystrokes, CPS
- Particle system: pool, renderer, spark effect
- Profile management and developer diagnostics

TODO:
- Full 3D rendering outlines/tracers using RenderLayer (planned, careful with render state)
- Complete UI polish (liquid-glass visuals, blur emulation)
- More particle presets and performance budgets
- Tests and render resource leak checks

*/

