package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SAVipDaysChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtVipDaysChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa vip days change", EvtVipDaysChange.class, SAVipDaysChangeEvent.class, "[SkriptSA ][on ]sa vip days change");
        EventValues.registerEventValue(SAVipDaysChangeEvent.class, Player.class, new Getter<Player, SAVipDaysChangeEvent>() {
            @Override
            public Player get(SAVipDaysChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SAVipDaysChangeEvent.class, Integer.class, new Getter<Integer, SAVipDaysChangeEvent>() {
            @Override
            public Integer get(SAVipDaysChangeEvent event) {
                return event.getOldDays();
            }
        }, 0);
        EventValues.registerEventValue(SAVipDaysChangeEvent.class, Integer.class, new Getter<Integer, SAVipDaysChangeEvent>() {
            @Override
            public Integer get(SAVipDaysChangeEvent event) {
                return event.getNewDays();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa vip days change"; }
}
