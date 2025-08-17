package net.nydt.nyskriptsa.events;

import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

    private final NySkriptSA plugin;

    public PlayerListener(NySkriptSA plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                SAUser saUser = Request.retreiveUser(player.getName());

                if (saUser != null) {
                    plugin.getDatabaseManager().saveUser(saUser);
                    plugin.getLogger().info("Hentede SuperAwesome data for " + player.getName() + " og gemte det i databasen.");
                } else {
                    plugin.getLogger().warning("Kunne ikke hente SuperAwesome data for " + player.getName() + ".");
                }
            }
        }.runTaskAsynchronously(plugin);
    }
}