package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SAServersChangeEvent;
import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtServersChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa servers change", EvtServersChange.class, SAServersChangeEvent.class, "[SkriptSA ][on ]sa servers change");
        EventValues.registerEventValue(SAServersChangeEvent.class, Player.class, new Getter<Player, SAServersChangeEvent>() {
            @Override
            public Player get(SAServersChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SAServersChangeEvent.class, SAUser.Server[].class, new Getter<SAUser.Server[], SAServersChangeEvent>() {
            @Override
            public SAUser.Server[] get(SAServersChangeEvent event) {
                return event.getAddedServers().toArray(new SAUser.Server[0]);
            }
        }, 0);
        EventValues.registerEventValue(SAServersChangeEvent.class, SAUser.Server[].class, new Getter<SAUser.Server[], SAServersChangeEvent>() {
            @Override
            public SAUser.Server[] get(SAServersChangeEvent event) {
                return event.getRemovedServers().toArray(new SAUser.Server[0]);
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa servers change"; }
}
