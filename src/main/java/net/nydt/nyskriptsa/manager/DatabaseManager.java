package net.nydt.nyskriptsa.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.nydt.nyskriptsa.NySkriptSA;
import net.nydt.nyskriptsa.objects.SAUser;
import net.nydt.nyskriptsa.objects.SAUser.Rate;
import net.nydt.nyskriptsa.objects.SAUser.Badge;
import net.nydt.nyskriptsa.objects.SAUser.Server;
import net.nydt.nyskriptsa.objects.SAUser.ServerBoost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class DatabaseManager {

    private final NySkriptSA plugin;
    private Connection connection;
    private final Gson gson = new Gson();

    public DatabaseManager(NySkriptSA plugin) {
        this.plugin = plugin;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/playerdata.db";
            this.connection = DriverManager.getConnection(url);
            plugin.getLogger().info("Forbundet til SQLite-databasen!");
        } catch (SQLException | ClassNotFoundException e) {
            plugin.getLogger().severe("Kunne ikke oprette forbindelse til databasen: " + e.getMessage());
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                plugin.getLogger().info("Forbindelsen til databasen er lukket.");
            } catch (SQLException e) {
                plugin.getLogger().severe("Kunne ikke lukke databaseforbindelsen: " + e.getMessage());
            }
        }
    }

    public void setupTables() {
        if (connection == null) {
            plugin.getLogger().severe("Kan ikke oprette tabeller: Ingen databaseforbindelse.");
            return;
        }
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "uuid TEXT PRIMARY KEY,"
                + "username TEXT NOT NULL,"
                + "lobbyPoints INTEGER,"
                + "playerPoints INTEGER,"
                + "vipDays INTEGER,"
                + "proDays INTEGER,"
                + "role TEXT,"
                + "discordId TEXT,"
                + "rates TEXT,"
                + "badges TEXT,"
                + "servers TEXT,"
                + "serverBoosts TEXT"
                + ");";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Kunne ikke oprette 'users' tabellen: " + e.getMessage(), e);
        }
    }

    public void saveUser(SAUser user) {
        if (connection == null) {
            plugin.getLogger().severe("Kan ikke gemme data: Ingen databaseforbindelse.");
            return;
        }
        String sql = "INSERT OR REPLACE INTO users (uuid, username, lobbyPoints, playerPoints, vipDays, proDays, role, discordId, rates, badges, servers, serverBoosts) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUuid());
            ps.setString(2, user.getUsername());
            ps.setInt(3, user.getLobbyPoints());
            ps.setInt(4, user.getPlayerPoints());
            ps.setInt(5, user.getVipDays() != null ? user.getVipDays() : 0);
            ps.setInt(6, user.getProDays() != null ? user.getProDays() : 0);
            ps.setString(7, user.getRole());
            ps.setString(8, user.getDiscordID());
            ps.setString(9, user.getRates() != null ? gson.toJson(user.getRates()) : "[]");
            ps.setString(10, user.getBadges() != null ? gson.toJson(user.getBadges()) : "[]");
            ps.setString(11, user.getServers() != null ? gson.toJson(user.getServers()) : "[]");
            ps.setString(12, user.getServerBoosts() != null ? gson.toJson(user.getServerBoosts()) : "[]");
            ps.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Kunne ikke gemme brugerdata for " + user.getUsername() + ": " + e.getMessage(), e);
        }
    }

    public SAUser getUser(String uuid) {
        if (connection == null) {
            plugin.getLogger().severe("Kan ikke hente data: Ingen databaseforbindelse.");
            return null;
        }
        String sql = "SELECT * FROM users WHERE uuid = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SAUser user = new SAUser();
                user.setUuid(rs.getString("uuid"));
                user.setUsername(rs.getString("username"));
                user.setLobbyPoints(rs.getInt("lobbyPoints"));
                user.setPlayerPoints(rs.getInt("playerPoints"));
                user.setVipDays(rs.getInt("vipDays"));
                user.setProDays(rs.getInt("proDays"));
                user.setRole(rs.getString("role"));
                user.setDiscordID(rs.getString("discordId"));
                user.setRates(gson.fromJson(rs.getString("rates"), new TypeToken<List<Rate>>(){}.getType()));
                user.setBadges(gson.fromJson(rs.getString("badges"), new TypeToken<List<Badge>>(){}.getType()));
                user.setServers(gson.fromJson(rs.getString("servers"), new TypeToken<List<Server>>(){}.getType()));
                user.setServerBoosts(gson.fromJson(rs.getString("serverBoosts"), new TypeToken<List<ServerBoost>>(){}.getType()));
                return user;
            }
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "Kunne ikke hente brugerdata med UUID " + uuid + ": " + e.getMessage(), e);
        }
        return null;
    }
}