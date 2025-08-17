package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.nydt.nyskriptsa.events.superawesome.SABadgesChangeEvent;
import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import org.jetbrains.annotations.Nullable;


public class EvtBadgesChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa badges change", EvtBadgesChange.class, SABadgesChangeEvent.class, "[SkriptSA ][on ]sa badges change");
        EventValues.registerEventValue(SABadgesChangeEvent.class, Player.class, new Getter<Player, SABadgesChangeEvent>() {
            @Override
            public Player get(SABadgesChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SABadgesChangeEvent.class, SAUser.Badge[].class, new Getter<SAUser.Badge[], SABadgesChangeEvent>() {
            @Override
            public SAUser.Badge[] get(SABadgesChangeEvent event) {
                return event.getAddedBadges().toArray(new SAUser.Badge[0]);
            }
        }, 0);
        EventValues.registerEventValue(SABadgesChangeEvent.class, SAUser.Badge[].class, new Getter<SAUser.Badge[], SABadgesChangeEvent>() {
            @Override
            public SAUser.Badge[] get(SABadgesChangeEvent event) {
                return event.getRemovedBadges().toArray(new SAUser.Badge[0]);
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa badges change"; }
}
