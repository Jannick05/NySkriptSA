package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.utils.RateUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerMinusRates extends SimplePropertyExpression<OfflinePlayer, Integer> {

    static {
        register(ExprPlayerMinusRates.class, Integer.class, "minus[ ]rates", "offlineplayer");
    }

    @Override
    @Nullable
    public Integer convert(OfflinePlayer player) {
        return RateUtils.countRatesByType(player, RateUtils.NEGATIVE);
    }

    @Override
    protected String getPropertyName() {
        return "minusrates";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
