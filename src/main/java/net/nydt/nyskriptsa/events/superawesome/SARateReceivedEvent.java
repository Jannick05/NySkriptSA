package net.nydt.nyskriptsa.events.superawesome;

import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;

public class SARateReceivedEvent extends SAUserUpdateEvent {
    private final Player receiver;
    private final SAUser.Rate newRate;

    public SARateReceivedEvent(SAUser oldUser, SAUser newUser, Player receiver, SAUser.Rate newRate) {
        super(oldUser, newUser);
        this.receiver = receiver;
        this.newRate = newRate;
    }

    public Player getReceiver() {
        return receiver;
    }

    public SAUser.Rate getNewRate() {
        return newRate;
    }

    public boolean isPositive() {
        return newRate.getType() != null && newRate.getType().equalsIgnoreCase("positive");
    }

    public boolean isNegative() {
        return newRate.getType() != null && newRate.getType().equalsIgnoreCase("negative");
    }
}
