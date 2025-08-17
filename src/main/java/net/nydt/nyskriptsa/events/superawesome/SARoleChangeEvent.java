package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;

public class SARoleChangeEvent extends SAUserUpdateEvent {
    private final Player player;
    public SARoleChangeEvent(SAUser oldUser, SAUser newUser, Player player) {
        super(oldUser, newUser);
        this.player = player;
    }
    public Player getPlayer() { return player; }
    public String getOldRole() { return oldUser.getRole(); }
    public String getNewRole() { return newUser.getRole(); }
}
