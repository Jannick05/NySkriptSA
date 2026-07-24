package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.utils.RateUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerPlusRates extends SimplePropertyExpression<OfflinePlayer, Integer> {

    static {
        register(ExprPlayerPlusRates.class, Integer.class, "plus[ ]rates", "offlineplayer");
    }

    @Override
    @Nullable
    public Integer convert(OfflinePlayer player) {
        return RateUtils.countRatesByType(player, RateUtils.POSITIVE);
    }

    @Override
    protected String getPropertyName() {
        return "plusrates";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
