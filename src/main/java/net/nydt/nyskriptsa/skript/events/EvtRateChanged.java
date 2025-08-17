package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.nydt.nyskriptsa.events.superawesome.SARateChangedEvent;
import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtRateChanged extends SkriptEvent {
    static {
        Skript.registerEvent("sa rate changed", EvtRateChanged.class, SARateChangedEvent.class, "[SkriptSA ][on ]sa rate changed");
        EventValues.registerEventValue(SARateChangedEvent.class, Player.class, new Getter<Player, SARateChangedEvent>() {
            @Override
            public Player get(SARateChangedEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SARateChangedEvent.class, SAUser.Rate.class, new Getter<SAUser.Rate, SARateChangedEvent>() {
            @Override
            public SAUser.Rate get(SARateChangedEvent event) {
                return event.getOldRate();
            }
        }, 0);
        EventValues.registerEventValue(SARateChangedEvent.class, SAUser.Rate.class, new Getter<SAUser.Rate, SARateChangedEvent>() {
            @Override
            public SAUser.Rate get(SARateChangedEvent event) {
                return event.getNewRate();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?>[] literals, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof SARateChangedEvent;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "sa rate changed event";
    }
}
