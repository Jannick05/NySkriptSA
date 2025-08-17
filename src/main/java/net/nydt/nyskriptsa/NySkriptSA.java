package net.nydt.nyskriptsa;

import ch.njol.skript.Skript;
import net.nydt.nyskriptsa.manager.DatabaseManager;
import net.nydt.nyskriptsa.events.PlayerListener;
import net.nydt.nyskriptsa.tasks.UpdateTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class NySkriptSA extends JavaPlugin {

    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        getLogger().info("Nyskriptsa Plugin er aktiveret!");

        // Denne linje sørger for, at plugin-mappen eksisterer
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        // Initialiserer databasen
        this.databaseManager = new DatabaseManager(this);
        databaseManager.connect();
        databaseManager.setupTables();

        // Registrer event-lytteren
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        if(getServer().getPluginManager().getPlugin("Skript") != null) try {
            Skript.registerAddon(this).loadClasses("net.nydt.nyskriptsa", "skript");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the addon.", e);
        }

        // Start den planlagte opgave
        new UpdateTask(this).runTaskTimer(this, 20L * 60L, 20L * 60L);
    }

    @Override
    public void onDisable() {
        getLogger().info("Nyskriptsa Plugin er deaktiveret!");
        if (databaseManager != null) {
            databaseManager.disconnect();
        }
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}