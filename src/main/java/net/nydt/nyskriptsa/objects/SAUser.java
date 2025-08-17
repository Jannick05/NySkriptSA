package net.nydt.nyskriptsa.objects;

import java.util.List;
import java.util.Objects;

public class SAUser {
    private String username;
    private String uuid;
    private String role;
    private Integer vipDays;
    private Integer proDays;
    private String lastSeen;
    private String discordID;
    private int lobbyPoints;
    private int playerPoints;
    private String status;
    private String currentServer;
    private List<String> lobbyAccess;
    private List<Rate> rates;
    private List<Badge> badges;
    private List<List<String>> permissions;
    private List<ServerBoost> serverBoosts;
    private List<Server> servers;
    private int rate;
    private boolean pro;
    private boolean vip;

    public String getUsername() { return username; }
    public String getUuid() { return uuid; }
    public String getRole() { return role; }
    public Integer getVipDays() { return vipDays; }
    public Integer getProDays() { return proDays; }
    public String getLastSeen() { return lastSeen; }
    public String getDiscordID() { return discordID; }
    public int getLobbyPoints() { return lobbyPoints; }
    public int getPlayerPoints() { return playerPoints; }
    public String getStatus() { return status; }
    public String getCurrentServer() { return currentServer; }
    public List<String> getLobbyAccess() { return lobbyAccess; }
    public List<Rate> getRates() { return rates; }
    public List<Badge> getBadges() { return badges; }
    public List<List<String>> getPermissions() { return permissions; }
    public List<ServerBoost> getServerBoosts() { return serverBoosts; }
    public List<Server> getServers() { return servers; }
    public int getRate() { return rate; }
    public boolean isPro() { return pro; }
    public boolean isVip() { return vip; }

    public void setUsername(String username) { this.username = username; }
    public void setUuid(String uuid) { this.uuid = uuid; }
    public void setRole(String role) { this.role = role; }
    public void setVipDays(Integer vipDays) { this.vipDays = vipDays; }
    public void setProDays(Integer proDays) { this.proDays = proDays; }
    public void setLastSeen(String lastSeen) { this.lastSeen = lastSeen; }
    public void setDiscordID(String discordID) { this.discordID = discordID; }
    public void setLobbyPoints(int lobbyPoints) { this.lobbyPoints = lobbyPoints; }
    public void setPlayerPoints(int playerPoints) { this.playerPoints = playerPoints; }
    public void setStatus(String status) { this.status = status; }
    public void setCurrentServer(String currentServer) { this.currentServer = currentServer; }
    public void setLobbyAccess(List<String> lobbyAccess) { this.lobbyAccess = lobbyAccess; }
    public void setRates(List<Rate> rates) { this.rates = rates; }
    public void setBadges(List<Badge> badges) { this.badges = badges; }
    public void setPermissions(List<List<String>> permissions) { this.permissions = permissions; }
    public void setServerBoosts(List<ServerBoost> serverBoosts) { this.serverBoosts = serverBoosts; }
    public void setServers(List<Server> servers) { this.servers = servers; }
    public void setRate(int rate) { this.rate = rate; }
    public void setPro(boolean pro) { this.pro = pro; }
    public void setVip(boolean vip) { this.vip = vip; }

    public static class Badge {
        private String name;
        private String color;
        private String symbol;
        private String active;
        private String created;

        public String getName() { return name; }
        public String getColor() { return color; }
        public String getSymbol() { return symbol; }
        public String getActive() { return active; }
        public String getCreated() { return created; }

        public void setName(String name) { this.name = name; }
        public void setColor(String color) { this.color = color; }
        public void setSymbol(String symbol) { this.symbol = symbol; }
        public void setActive(String active) { this.active = active; }
        public void setCreated(String created) { this.created = created; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Badge badge = (Badge) o;
            return Objects.equals(name, badge.name);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static class Rate {
        private String username;
        private String uuid;
        private String role;
        private int points;
        private String type;
        private String created;

        public String getUsername() { return username; }
        public String getUuid() { return uuid; }
        public String getRole() { return role; }
        public String getCreated() { return created; }
        public String getType() { return type; }
        public int getPoints() { return points; }
        public void setUsername(String username) { this.username = username; }
        public void setUuid(String uuid) { this.uuid = uuid; }
        public void setRole(String role) { this.role = role; }
        public void setCreated(String created) { this.created = created; }
        public void setType(String type) { this.type = type; }
        public void setPoints(int points) { this.points = points; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rate rate = (Rate) o;
            return points == rate.points &&
                    Objects.equals(uuid, rate.uuid) &&
                    Objects.equals(type, rate.type);
        }
        @Override
        public int hashCode() {
            return Objects.hash(uuid, points, type);
        }
    }

    public static class ServerBoost {
        private String name;
        private String since;

        public String getName() { return name; }
        public String getSince() { return since; }
        public void setName(String name) { this.name = name; }
        public void setSince(String since) { this.since = since; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ServerBoost that = (ServerBoost) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(since, that.since);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name, since);
        }
    }

    public static class Server {
        private String name;
        private String status;
        private String version;

        public String getName() { return name; }
        public String getStatus() { return status; }
        public String getVersion() { return version; }
        public void setName(String name) { this.name = name; }
        public void setStatus(String status) { this.status = status; }
        public void setVersion(String version) { this.version = version; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Server server = (Server) o;
            return Objects.equals(name, server.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}