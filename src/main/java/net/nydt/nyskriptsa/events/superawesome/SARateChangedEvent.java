package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class SARateChangedEvent extends SAUserUpdateEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final SAUser.Rate oldRate;
    private final SAUser.Rate newRate;

    public SARateChangedEvent(SAUser oldUser, SAUser newUser, Player player, SAUser.Rate oldRate, SAUser.Rate newRate) {
        super(oldUser, newUser);
        this.player = player;
        this.oldRate = oldRate;
        this.newRate = newRate;
    }

    public Player getPlayer() {
        return player;
    }

    public SAUser.Rate getOldRate() {
        return oldRate;
    }

    public SAUser.Rate getNewRate() {
        return newRate;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
