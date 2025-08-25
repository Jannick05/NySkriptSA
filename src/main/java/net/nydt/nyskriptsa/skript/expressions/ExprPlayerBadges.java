package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerBadges extends SimplePropertyExpression<OfflinePlayer, Integer> {

    static {
        register(ExprPlayerBadges.class, Integer.class, "badges", "offlineplayer");
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

        return (user != null) ? user.getBadges().size() : 0;
    }

    @Override
    protected String getPropertyName() {
        return "badges";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
