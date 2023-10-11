package fr.edminecoreteam.proxystaff.data.uuid;

import fr.edminecoreteam.proxystaff.edorm.MySQL;
import net.md_5.bungee.api.ProxyServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UUIDData {
    private static String p;
    public UUIDData(String p){ this.p = p;}

    public String getUUID(){
        if(ProxyServer.getInstance().getPlayer(p) != null){
            return ProxyServer.getInstance().getPlayer(p).getUniqueId().toString();
        }else{
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT player_uuid FROM ed_accounts WHERE player_name = ?");
                ps.setString(1, p);
                String response = null;
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    response = rs.getString("player_uuid");
                }
                ps.close();
                return response;

            }
            catch (SQLException e){
                e.toString();
            }
        }

        return null;
    }
}
