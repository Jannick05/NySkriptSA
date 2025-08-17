package net.nydt.nyskriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.jetbrains.annotations.Nullable;
import net.nydt.nyskriptsa.events.superawesome.SARateReceivedEvent;
import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EvtRateReceived extends SkriptEvent {
    static {
        Skript.registerEvent("sa rate received", EvtRateReceived.class, SARateReceivedEvent.class, "[SkriptSA ][on ]sa rate received");
        EventValues.registerEventValue(SARateReceivedEvent.class, Player.class, new Getter<Player, SARateReceivedEvent>() {
            @Override
            public Player get(SARateReceivedEvent event) {
                return event.getReceiver();
            }
        }, 0);
        EventValues.registerEventValue(SARateReceivedEvent.class, String.class, new Getter<String, SARateReceivedEvent>() {
            @Override
            public String get(SARateReceivedEvent event) {
                return event.getNewRate().getUsername();
            }
        }, 0);
        EventValues.registerEventValue(SARateReceivedEvent.class, SAUser.Rate.class, new Getter<SAUser.Rate, SARateReceivedEvent>() {
            @Override
            public SAUser.Rate get(SARateReceivedEvent event) {
                return event.getNewRate();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) { return true; }
    @Override
    public boolean check(Event event) { return true; }
    @Override
    public String toString(@Nullable Event event, boolean b) { return "on sa rate received"; }
}
