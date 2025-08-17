package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SAProDaysChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtProDaysChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa pro days change", EvtProDaysChange.class, SAProDaysChangeEvent.class, "[SkriptSA ][on ]sa pro days change");
        EventValues.registerEventValue(SAProDaysChangeEvent.class, Player.class, new Getter<Player, SAProDaysChangeEvent>() {
            @Override
            public Player get(SAProDaysChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SAProDaysChangeEvent.class, Integer.class, new Getter<Integer, SAProDaysChangeEvent>() {
            @Override
            public Integer get(SAProDaysChangeEvent event) {
                return event.getOldDays();
            }
        }, 0);
        EventValues.registerEventValue(SAProDaysChangeEvent.class, Integer.class, new Getter<Integer, SAProDaysChangeEvent>() {
            @Override
            public Integer get(SAProDaysChangeEvent event) {
                return event.getNewDays();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa pro days change"; }
}
