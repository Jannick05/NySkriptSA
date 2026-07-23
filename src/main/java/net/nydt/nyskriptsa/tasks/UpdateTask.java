package net.nydt.nyskriptsa.tasks;

import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.events.superawesome.*;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.function.BiConsumer;

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

                    SAUser newUser = Request.retrieveUser(player.getName());

                    if (newUser == null) {
                        plugin.getLogger().warning("Kunne ikke hente opdaterede data fra API'en for " + player.getName() + ".");
                        return;
                    }

                    if (oldUser.getLobbyPoints() != newUser.getLobbyPoints()) {
                        Bukkit.getPluginManager().callEvent(new SALobbyPointsChangeEvent(oldUser, newUser, player));
                    }
                    if (oldUser.getPlayerPoints() != newUser.getPlayerPoints()) {
                        Bukkit.getPluginManager().callEvent(new SAPlayerPointsChangeEvent(oldUser, newUser, player));
                    }

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
                                Bukkit.getPluginManager().callEvent(new SARateReceivedEvent(oldUser, newUser, player, newRate));
                            } else {
                                if (oldRate.getPoints() != newRate.getPoints() || !Objects.equals(oldRate.getType(), newRate.getType())) {
                                    Bukkit.getPluginManager().callEvent(new SARateChangedEvent(oldUser, newUser, player, oldRate, newRate));
                                }
                            }
                        }
                    }

                    checkSetChanges(
                            oldUser.getBadges(),
                            newUser.getBadges(),
                            (added, removed) -> Bukkit.getPluginManager().callEvent(new SABadgesChangeEvent(oldUser, newUser, player, added, removed))
                    );

                    checkSetChanges(
                            oldUser.getServerBoosts(),
                            newUser.getServerBoosts(),
                            (added, removed) -> Bukkit.getPluginManager().callEvent(new SAServerBoostsChangeEvent(oldUser, newUser, player, added, removed))
                    );

                    checkSetChanges(
                            oldUser.getServers(),
                            newUser.getServers(),
                            (added, removed) -> Bukkit.getPluginManager().callEvent(new SAServersChangeEvent(oldUser, newUser, player, added, removed))
                    );

                    plugin.getDatabaseManager().saveUser(newUser);

                }
            }.runTaskAsynchronously(plugin);
        }
    }

    private <T> void checkSetChanges(List<T> oldList, List<T> newList, BiConsumer<Set<T>, Set<T>> onChange) {
        Set<T> oldSet = new HashSet<>(oldList != null ? oldList : Collections.emptySet());
        Set<T> newSet = new HashSet<>(newList != null ? newList : Collections.emptySet());
        Set<T> added = new HashSet<>(newSet);
        added.removeAll(oldSet);
        Set<T> removed = new HashSet<>(oldSet);
        removed.removeAll(newSet);

        if (!added.isEmpty() || !removed.isEmpty()) {
            onChange.accept(added, removed);
        }
    }
}