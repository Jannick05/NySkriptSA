package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SAServerBoostsChangeEvent;
import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtServerBoostsChange extends SkriptEvent {
    static {
        Skript.registerEvent("sa server boosts change", EvtServerBoostsChange.class, SAServerBoostsChangeEvent.class, "[SkriptSA ][on ]sa server boosts change");
        EventValues.registerEventValue(SAServerBoostsChangeEvent.class, Player.class, new Getter<Player, SAServerBoostsChangeEvent>() {
            @Override
            public Player get(SAServerBoostsChangeEvent event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(SAServerBoostsChangeEvent.class, SAUser.ServerBoost[].class, new Getter<SAUser.ServerBoost[], SAServerBoostsChangeEvent>() {
            @Override
            public SAUser.ServerBoost[] get(SAServerBoostsChangeEvent event) {
                return event.getAddedBoosts().toArray(new SAUser.ServerBoost[0]);
            }
        }, 0);
        EventValues.registerEventValue(SAServerBoostsChangeEvent.class, SAUser.ServerBoost[].class, new Getter<SAUser.ServerBoost[], SAServerBoostsChangeEvent>() {
            @Override
            public SAUser.ServerBoost[] get(SAServerBoostsChangeEvent event) {
                return event.getRemovedBoosts().toArray(new SAUser.ServerBoost[0]);
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa server boosts change"; }
}
