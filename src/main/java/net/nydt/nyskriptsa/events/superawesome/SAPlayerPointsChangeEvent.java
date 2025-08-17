package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class SAPlayerPointsChangeEvent extends SAUserUpdateEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;

    public SAPlayerPointsChangeEvent(SAUser oldUser, SAUser newUser, Player player) {
        super(oldUser, newUser);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getOldPoints() {
        return oldUser.getPlayerPoints();
    }

    public int getNewPoints() {
        return newUser.getPlayerPoints();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
