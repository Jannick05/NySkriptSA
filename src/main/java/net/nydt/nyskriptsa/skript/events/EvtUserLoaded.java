package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.nydt.nyskriptsa.events.SAUserLoadedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public final class EvtUserLoaded extends SkriptEvent {
    static {
        Skript.registerEvent("sa user loaded", EvtUserLoaded.class, SAUserLoadedEvent.class,
                "[SkriptSA ][on ]sa user loaded");
        EventValues.registerEventValue(SAUserLoadedEvent.class, Player.class,
                new Getter<Player, SAUserLoadedEvent>() {
                    @Override public Player get(SAUserLoadedEvent event) { return event.getPlayer(); }
                }, 0);
    }

    @Override public boolean init(Literal<?>[] literals, int matchedPattern, SkriptParser.ParseResult result) {
        return true;
    }
    @Override public boolean check(Event event) { return event instanceof SAUserLoadedEvent; }
    @Override public String toString(@Nullable Event event, boolean debug) { return "sa user loaded"; }
}
