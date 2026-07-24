package net.nydt.nyskriptsa.utils;

import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RateUtils {

    public static final String POSITIVE = "positive";
    public static final String NEGATIVE = "negative";

    private RateUtils() {
    }

    public static SAUser retrieveUser(OfflinePlayer player) {
        if (player == null) {
            return null;
        }

        try {
            if (player.isOnline()) {
                NySkriptSA plugin = NySkriptSA.getInstance();
                if (plugin == null || plugin.getDatabaseManager() == null || player.getUniqueId() == null) {
                    return null;
                }
                return plugin.getDatabaseManager().getUser(player.getUniqueId().toString());
            }

            String name = player.getName();
            return name != null ? Request.retrieveUser(name) : null;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static int countRates(OfflinePlayer player) {
        SAUser user = retrieveUser(player);
        return user != null && user.getRates() != null ? user.getRates().size() : 0;
    }

    public static int countRatesByType(OfflinePlayer player, String type) {
        int count = 0;
        for (SAUser.Rate rate : getRates(player)) {
            if (matchesType(rate, type)) {
                count++;
            }
        }
        return count;
    }

    public static String[] getRateUsernames(OfflinePlayer player, String type) {
        List<String> usernames = new ArrayList<String>();
        for (SAUser.Rate rate : getRates(player)) {
            if ((type == null || matchesType(rate, type)) && rate.getUsername() != null) {
                usernames.add(rate.getUsername());
            }
        }
        return usernames.toArray(new String[0]);
    }

    private static List<SAUser.Rate> getRates(OfflinePlayer player) {
        SAUser user = retrieveUser(player);
        if (user == null || user.getRates() == null) {
            return Collections.emptyList();
        }
        return user.getRates();
    }

    private static boolean matchesType(SAUser.Rate rate, String type) {
        return rate != null && rate.getType() != null && rate.getType().equalsIgnoreCase(type);
    }
}
