package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerProDays extends SimplePropertyExpression<OfflinePlayer, Integer> {

    static {
        register(ExprPlayerProDays.class, Integer.class, "pro days", "offlineplayer");
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

        return (user != null) ? user.getProDays() : 0;
    }

    @Override
    protected String getPropertyName() {
        return "pro days";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
