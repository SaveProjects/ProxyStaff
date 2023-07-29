package fr.edminecoreteam.proxystaff.utils;

import fr.edminecoreteam.proxystaff.Main;
import fr.edminecoreteam.proxystaff.account.RankInfo;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager {

    private ProxiedPlayer p;
    private static Main main = Main.getInstance();

    public PlayerManager(ProxiedPlayer p){
        this.p = p;
    }

    public static boolean hasPermission(ProxiedPlayer p){
        RankInfo rankInfo = new RankInfo(p);
        if(rankInfo.getRankModule() >= 13){
            return true;
        }
        return false;
    }


    public static boolean isInModerationMod(ProxiedPlayer p){
        return main.modList.contains(p.getUniqueId());
    }

    public void removeP(){
        main.players.remove(p.getUniqueId());
        main.modList.remove(p.getUniqueId());
        sendPluginMessage.sendPlayerModActivation(p, getStaffList(), false);
    }

    public void init(){
        main.modList.add(p.getUniqueId());
        main.players.put(p.getUniqueId(), this);
        sendPluginMessage.sendPlayerModActivation(p, getStaffList(), true);
    }

    public ArrayList<UUID> getStaffList() {
        ArrayList<UUID> staffList = new ArrayList<>();
        for (ProxiedPlayer players : main.getProxy().getPlayers()) {
            if(hasPermission(players) && players != p){
                staffList.add(players.getUniqueId());
            }
        }
        return staffList;
    }

    public void setVanished(boolean vanished){
        if(vanished){
            if(!main.vanishList.contains(p.getUniqueId())){
                main.vanishList.add(p.getUniqueId());
            }
        }
    }
}
