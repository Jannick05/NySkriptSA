package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.nydt.nyskriptsa.utils.RateUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerMinusRateList extends SimpleExpression<String> {

    private Expression<OfflinePlayer> player;

    static {
        Skript.registerExpression(
                ExprPlayerMinusRateList.class,
                String.class,
                ExpressionType.PROPERTY,
                "%offlineplayer%'[s] minus[ ]rate[s][ ]list[e]"
        );
    }

    @Override
    protected String[] get(Event event) {
        OfflinePlayer offlinePlayer = player != null ? player.getSingle(event) : null;
        return RateUtils.getRateUsernames(offlinePlayer, RateUtils.NEGATIVE);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<OfflinePlayer>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return player + "'s minusratesliste";
    }
}
