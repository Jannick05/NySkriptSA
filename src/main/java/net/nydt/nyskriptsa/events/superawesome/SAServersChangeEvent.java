package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.Set;

public class SAServersChangeEvent extends SAUserUpdateEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Set<SAUser.Server> addedServers;
    private final Set<SAUser.Server> removedServers;

    public SAServersChangeEvent(SAUser oldUser, SAUser newUser, Player player, Set<SAUser.Server> addedServers, Set<SAUser.Server> removedServers) {
        super(oldUser, newUser);
        this.player = player;
        this.addedServers = addedServers;
        this.removedServers = removedServers;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<SAUser.Server> getAddedServers() {
        return addedServers;
    }

    public Set<SAUser.Server> getRemovedServers() {
        return removedServers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
