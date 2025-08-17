package net.nydt.nyskriptsa.tasks;

import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.events.superawesome.*;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class UpdateTask extends BukkitRunnable {

    private final NySkriptSA plugin;

    public UpdateTask(NySkriptSA plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getLogger().info("Starter periodisk API-opdatering for online spillere...");

        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();

            new BukkitRunnable() {
                @Override
                public void run() {
                    SAUser oldUser = plugin.getDatabaseManager().getUser(uuid.toString());

                    if (oldUser == null) {
                        plugin.getLogger().warning("Brugerdata for " + player.getName() + " er ikke fundet i databasen.");
                        return;
                    }

                    SAUser newUser = Request.retreiveUser(player.getName());

                    if (newUser == null) {
                        plugin.getLogger().warning("Kunne ikke hente opdaterede data fra API'en for " + player.getName() + ".");
                        return;
                    }

                    // Sammenlign point
                    if (oldUser.getLobbyPoints() != newUser.getLobbyPoints()) {
                        Bukkit.getPluginManager().callEvent(new SALobbyPointsChangeEvent(oldUser, newUser, player));
                    }
                    if (oldUser.getPlayerPoints() != newUser.getPlayerPoints()) {
                        Bukkit.getPluginManager().callEvent(new SAPlayerPointsChangeEvent(oldUser, newUser, player));
                    }

                    // Sammenlign roller og dage (null-sikker)
                    if (!Objects.equals(oldUser.getRole(), newUser.getRole())) {
                        Bukkit.getPluginManager().callEvent(new SARoleChangeEvent(oldUser, newUser, player));
                    }
                    if (!Objects.equals(oldUser.getVipDays(), newUser.getVipDays())) {
                        Bukkit.getPluginManager().callEvent(new SAVipDaysChangeEvent(oldUser, newUser, player));
                    }
                    if (!Objects.equals(oldUser.getProDays(), newUser.getProDays())) {
                        Bukkit.getPluginManager().callEvent(new SAProDaysChangeEvent(oldUser, newUser, player));
                    }

                    if (oldUser.getRates() != null && newUser.getRates() != null) {
                        Map<String, SAUser.Rate> oldRatesByUuid = new HashMap<>();
                        for (SAUser.Rate rate : oldUser.getRates()) {
                            oldRatesByUuid.put(rate.getUuid(), rate);
                        }

                        for (SAUser.Rate newRate : newUser.getRates()) {
                            SAUser.Rate oldRate = oldRatesByUuid.get(newRate.getUuid());

                            if (oldRate == null) {
                                // Ny rate tilføjet
                                Bukkit.getPluginManager().callEvent(new SARateReceivedEvent(oldUser, newUser, player, newRate));
                            } else {
                                // Eksisterende rate ændret
                                if (oldRate.getPoints() != newRate.getPoints() || !Objects.equals(oldRate.getType(), newRate.getType())) {
                                    Bukkit.getPluginManager().callEvent(new SARateChangedEvent(oldUser, newUser, player, oldRate, newRate));
                                }
                            }
                        }
                    }

                    // Badges
                    Set<SAUser.Badge> oldBadges = new HashSet<>(oldUser.getBadges() != null ? oldUser.getBadges() : Collections.emptySet());
                    Set<SAUser.Badge> newBadges = new HashSet<>(newUser.getBadges() != null ? newUser.getBadges() : Collections.emptySet());
                    Set<SAUser.Badge> addedBadges = new HashSet<>(newBadges);
                    addedBadges.removeAll(oldBadges);
                    Set<SAUser.Badge> removedBadges = new HashSet<>(oldBadges);
                    removedBadges.removeAll(newBadges);

                    if (!addedBadges.isEmpty() || !removedBadges.isEmpty()) {
                        Bukkit.getPluginManager().callEvent(new SABadgesChangeEvent(oldUser, newUser, player, addedBadges, removedBadges));
                    }

                    // ServerBoosts
                    Set<SAUser.ServerBoost> oldBoosts = new HashSet<>(oldUser.getServerBoosts() != null ? oldUser.getServerBoosts() : Collections.emptySet());
                    Set<SAUser.ServerBoost> newBoosts = new HashSet<>(newUser.getServerBoosts() != null ? newUser.getServerBoosts() : Collections.emptySet());
                    Set<SAUser.ServerBoost> addedBoosts = new HashSet<>(newBoosts);
                    addedBoosts.removeAll(oldBoosts);
                    Set<SAUser.ServerBoost> removedBoosts = new HashSet<>(oldBoosts);
                    removedBoosts.removeAll(newBoosts);

                    if (!addedBoosts.isEmpty() || !removedBoosts.isEmpty()) {
                        Bukkit.getPluginManager().callEvent(new SAServerBoostsChangeEvent(oldUser, newUser, player, addedBoosts, removedBoosts));
                    }

                    // Servers
                    Set<SAUser.Server> oldServers = new HashSet<>(oldUser.getServers() != null ? oldUser.getServers() : Collections.emptySet());
                    Set<SAUser.Server> newServers = new HashSet<>(newUser.getServers() != null ? newUser.getServers() : Collections.emptySet());
                    Set<SAUser.Server> addedServers = new HashSet<>(newServers);
                    addedServers.removeAll(oldServers);
                    Set<SAUser.Server> removedServers = new HashSet<>(oldServers);
                    removedServers.removeAll(newServers);

                    if (!addedServers.isEmpty() || !removedServers.isEmpty()) {
                        Bukkit.getPluginManager().callEvent(new SAServersChangeEvent(oldUser, newUser, player, addedServers, removedServers));
                    }

                    // Gem de nye data i databasen
                    plugin.getDatabaseManager().saveUser(newUser);

                }
            }.runTaskAsynchronously(plugin);
        }
    }
}