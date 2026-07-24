package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.utils.RateUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerRates extends SimplePropertyExpression<OfflinePlayer, Integer> {

    static {
        register(ExprPlayerRates.class, Integer.class, "rates", "offlineplayer");
    }

    @Override
    @Nullable
    public Integer convert(OfflinePlayer player) {
        return RateUtils.countRates(player);
    }

    @Override
    protected String getPropertyName() {
        return "rates";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
