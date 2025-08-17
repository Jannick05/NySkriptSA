package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SAUserUpdateEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    protected final SAUser oldUser;
    protected final SAUser newUser;

    public SAUserUpdateEvent(SAUser oldUser, SAUser newUser) {
        this.oldUser = oldUser;
        this.newUser = newUser;
    }

    public SAUser getOldUser() {
        return oldUser;
    }

    public SAUser getNewUser() {
        return newUser;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
