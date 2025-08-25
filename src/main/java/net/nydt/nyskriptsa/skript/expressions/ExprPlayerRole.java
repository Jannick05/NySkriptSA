package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerRole extends SimplePropertyExpression<OfflinePlayer, String> {

    static {
        register(ExprPlayerRole.class, String.class, "role", "offlineplayer");
    }

    @Override
    @Nullable
    public String convert(OfflinePlayer player) {
        SAUser user;

        if (player.isOnline()) {
            user = NySkriptSA.getInstance().getDatabaseManager().getUser(player.getUniqueId().toString());
        } else {
            user = Request.retreiveUser(player.getName());
        }

        return (user != null && user.getRole() != null) ? user.getRole() : "none";
    }

    @Override
    protected String getPropertyName() {
        return "role";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
