package net.nydt.nyskriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.utils.Request;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public final class ExprPlayerServerBooster extends SimplePropertyExpression<OfflinePlayer, Boolean> {
    static {
        register(ExprPlayerServerBooster.class, Boolean.class, "server booster", "offlineplayer");
    }

    @Override
    @Nullable
    public Boolean convert(OfflinePlayer player) {
        SAUser user = player.isOnline()
                ? NySkriptSA.getInstance().getDatabaseManager().getUser(player.getUniqueId().toString())
                : Request.retrieveUser(player.getName());
        return user != null && user.getServerBoosts() != null && !user.getServerBoosts().isEmpty();
    }

    @Override protected String getPropertyName() { return "server booster"; }
    @Override public Class<? extends Boolean> getReturnType() { return Boolean.class; }
}
