package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class SALobbyPointsChangeEvent extends SAUserUpdateEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public SALobbyPointsChangeEvent(SAUser oldUser, SAUser newUser, Player player) {
        super(oldUser, newUser);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getOldPoints() {
        return oldUser.getLobbyPoints();
    }

    public int getNewPoints() {
        return newUser.getLobbyPoints();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
