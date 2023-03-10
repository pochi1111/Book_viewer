package test.official_book.official_book;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import test.official_book.official_book.Commands.book_open;

public final class Official_book extends JavaPlugin {

    public static JavaPlugin plugin;
    private Listeners listeners;

    @Override
    public void onEnable() {
        plugin = this;
        this.listeners = new Listeners();
        Bukkit.getPluginManager().registerEvents(this.listeners, this);

        // Plugin startup logic
        getCommand("book_viewer").setExecutor(new book_open());
        super.onEnable();
        plugin.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
