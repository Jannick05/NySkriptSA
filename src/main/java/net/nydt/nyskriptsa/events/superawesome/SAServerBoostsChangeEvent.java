package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;

import java.util.Set;

public class SAServerBoostsChangeEvent extends SAUserUpdateEvent {
    private final Player player;
    private final Set<SAUser.ServerBoost> addedBoosts;
    private final Set<SAUser.ServerBoost> removedBoosts;
    public SAServerBoostsChangeEvent(SAUser oldUser, SAUser newUser, Player player, Set<SAUser.ServerBoost> addedBoosts, Set<SAUser.ServerBoost> removedBoosts) {
        super(oldUser, newUser);
        this.player = player;
        this.addedBoosts = addedBoosts;
        this.removedBoosts = removedBoosts;
    }
    public Player getPlayer() { return player; }
    public Set<SAUser.ServerBoost> getAddedBoosts() { return addedBoosts; }
    public Set<SAUser.ServerBoost> getRemovedBoosts() { return removedBoosts; }
    public boolean hasAddedBoosts() { return !addedBoosts.isEmpty(); }
    public boolean hasRemovedBoosts() { return !removedBoosts.isEmpty(); }
}
