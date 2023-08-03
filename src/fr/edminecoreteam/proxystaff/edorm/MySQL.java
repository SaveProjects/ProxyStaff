package fr.edminecoreteam.proxystaff.edorm;

import fr.edminecoreteam.proxystaff.Main;

import java.sql.SQLException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class MySQL
{
    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;
    private static Connection connection;

    public MySQL(String urlBase, String host, String database, String userName, String password) {
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
        this.connexion();
    }

    public static Connection getConnection() {
        return connection;
    }

    public void connexion() {
        if (!isOnline()) {
            try {
                connection = DriverManager.getConnection(String.valueOf(this.urlBase) + this.host + "/" + this.database, this.userName, this.password);
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("§cErreur de connexion a la base de donnée...");
            }
        }
    }

    public void deconnexion() {
        if (isOnline()) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline() {
        try {
            return connection != null && !connection.isClosed();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void creatingTableMute() {
        try {
            PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_mute (" +
                    "`mute_id` int NOT NULL AUTO_INCREMENT, " +
                    "`player_uuid` varchar(255) NOT NULL, " +
                    "`reason` varchar(255), " +
                    "`mute_by_uuid` varchar(255) NOT NULL, " +
                    "`mute_by_name` varchar(255) NOT NULL, " +
                    "`confirmed_by_uuid` varchar(255) DEFAULT NULL, " +
                    "`confirmed_by_name` varchar(255) DEFAULT NULL, " +
                    "`mute_date` bigint(20) NOT NULL, " +
                    "`mute_end_time` bigint(20) DEFAULT NULL, " +
                    "PRIMARY KEY (`mute_id`), UNIQUE(`mute_id`))");
            stm.execute();
            stm.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}