package net.nydt.nyskriptsa.events;

import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.Bukkit;
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
        String playerName = player.getName();
        String uuid = player.getUniqueId().toString();

        new BukkitRunnable() {
            @Override
            public void run() {
                SAUser saUser = Request.retrieveUser(playerName);
                if (saUser == null) {
                    plugin.getLogger().warning("Kunne ikke hente SuperAwesome data for " + playerName + ".");
                    return;
                }
                saUser.setUuid(uuid);
                plugin.getDatabaseManager().saveUser(saUser);
                Bukkit.getScheduler().runTask(plugin, () -> {
                    if (player.isOnline()) {
                        Bukkit.getPluginManager().callEvent(new SAUserLoadedEvent(player, saUser));
                    }
                });
            }
        }.runTaskAsynchronously(plugin);
    }
}
