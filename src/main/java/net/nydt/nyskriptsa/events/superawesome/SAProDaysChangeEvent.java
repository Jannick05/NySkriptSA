package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;

public class SAProDaysChangeEvent extends SAUserUpdateEvent {
    private final Player player;
    public SAProDaysChangeEvent(SAUser oldUser, SAUser newUser, Player player) {
        super(oldUser, newUser);
        this.player = player;
    }
    public Player getPlayer() { return player; }
    public int getOldDays() { return oldUser.getProDays(); }
    public int getNewDays() { return newUser.getProDays(); }
}
