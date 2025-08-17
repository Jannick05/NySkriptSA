package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SALobbyPointsChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtLobbyPointsChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa lobby points change", EvtLobbyPointsChange.class, SALobbyPointsChangeEvent.class, "[SkriptSA ][on ]sa lobby points change");
        EventValues.registerEventValue(SALobbyPointsChangeEvent.class, Player.class, new Getter<Player, SALobbyPointsChangeEvent>() {
            @Override
            public Player get(SALobbyPointsChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SALobbyPointsChangeEvent.class, Integer.class, new Getter<Integer, SALobbyPointsChangeEvent>() {
            @Override
            public Integer get(SALobbyPointsChangeEvent event) {
                return event.getOldPoints();
            }
        }, 0);
        EventValues.registerEventValue(SALobbyPointsChangeEvent.class, Integer.class, new Getter<Integer, SALobbyPointsChangeEvent>() {
            @Override
            public Integer get(SALobbyPointsChangeEvent event) {
                return event.getNewPoints();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa lobby points change"; }
}
