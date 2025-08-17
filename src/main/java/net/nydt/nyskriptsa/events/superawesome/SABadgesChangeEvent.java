package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;

import java.util.Set;

public class SABadgesChangeEvent extends SAUserUpdateEvent {
    private final Player player;
    private final Set<SAUser.Badge> addedBadges;
    private final Set<SAUser.Badge> removedBadges;
    public SABadgesChangeEvent(SAUser oldUser, SAUser newUser, Player player, Set<SAUser.Badge> addedBadges, Set<SAUser.Badge> removedBadges) {
        super(oldUser, newUser);
        this.player = player;
        this.addedBadges = addedBadges;
        this.removedBadges = removedBadges;
    }
    public Player getPlayer() { return player; }
    public Set<SAUser.Badge> getAddedBadges() { return addedBadges; }
    public Set<SAUser.Badge> getRemovedBadges() { return removedBadges; }
    public boolean hasAddedBadges() { return !addedBadges.isEmpty(); }
    public boolean hasRemovedBadges() { return !removedBadges.isEmpty(); }
}
