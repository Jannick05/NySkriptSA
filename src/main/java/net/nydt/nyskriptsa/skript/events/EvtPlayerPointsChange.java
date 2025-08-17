package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SAPlayerPointsChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtPlayerPointsChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa player points change", EvtPlayerPointsChange.class, SAPlayerPointsChangeEvent.class, "[SkriptSA ][on ]sa player points change");
        EventValues.registerEventValue(SAPlayerPointsChangeEvent.class, Player.class, new Getter<Player, SAPlayerPointsChangeEvent>() {
            @Override
            public Player get(SAPlayerPointsChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SAPlayerPointsChangeEvent.class, Integer.class, new Getter<Integer, SAPlayerPointsChangeEvent>() {
            @Override
            public Integer get(SAPlayerPointsChangeEvent event) {
                return event.getOldPoints();
            }
        }, 0);
        EventValues.registerEventValue(SAPlayerPointsChangeEvent.class, Integer.class, new Getter<Integer, SAPlayerPointsChangeEvent>() {
            @Override
            public Integer get(SAPlayerPointsChangeEvent event) {
                return event.getNewPoints();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa player points change"; }
}