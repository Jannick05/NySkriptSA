package net.nydt.nyskriptsa.events;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class SAUserLoadedEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final SAUser user;

    public SAUserLoadedEvent(Player player, SAUser user) {
        this.player = player;
        this.user = user;
    }

    public Player getPlayer() { return player; }
    public SAUser getUser() { return user; }
    @Override public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
