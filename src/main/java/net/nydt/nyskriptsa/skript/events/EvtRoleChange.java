package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SARoleChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtRoleChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa role change", EvtRoleChange.class, SARoleChangeEvent.class, "[SkriptSA ][on ]sa role change");
        EventValues.registerEventValue(SARoleChangeEvent.class, Player.class, new Getter<Player, SARoleChangeEvent>() {
            @Override
            public Player get(SARoleChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SARoleChangeEvent.class, String.class, new Getter<String, SARoleChangeEvent>() {
            @Override
            public String get(SARoleChangeEvent event) {
                return event.getOldRole();
            }
        }, 0);
        EventValues.registerEventValue(SARoleChangeEvent.class, String.class, new Getter<String, SARoleChangeEvent>() {
            @Override
            public String get(SARoleChangeEvent event) {
                return event.getNewRole();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa role change"; }
}
