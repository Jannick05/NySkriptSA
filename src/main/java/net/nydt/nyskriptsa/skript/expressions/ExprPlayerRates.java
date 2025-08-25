package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerRates extends SimplePropertyExpression<OfflinePlayer, Integer> {

    static {
        register(ExprPlayerRates.class, Integer.class, "rates", "offlineplayer");
    }

    @Override
    @Nullable
    public Integer convert(OfflinePlayer player) {
        SAUser user;

        if (player.isOnline()) {
            user = NySkriptSA.getInstance().getDatabaseManager().getUser(player.getUniqueId().toString());
        } else {
            user = Request.retreiveUser(player.getName());
        }

        return (user != null) ? user.getRates().size() : 0;
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
