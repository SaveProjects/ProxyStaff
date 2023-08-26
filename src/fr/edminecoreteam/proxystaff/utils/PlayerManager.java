package fr.edminecoreteam.proxystaff.utils;

import fr.edminecoreteam.proxystaff.Main;
import fr.edminecoreteam.proxystaff.account.rank.RankInfo;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager {

    private ProxiedPlayer p;
    private static Main main = Main.getInstance();

    public PlayerManager(ProxiedPlayer p){
        this.p = p;
    }

    public static boolean hasPermission(ProxiedPlayer p, int permission){
        if(p != null){
            RankInfo rankInfo = new RankInfo(p);
            if(rankInfo.getRankModule() >= permission){
                return true;
            }
        }
        return false;
    }

    public static PlayerManager getFromPlayer(ProxiedPlayer p){
        return main.players.get(p.getUniqueId());
    }

    public static boolean isInModerationMod(ProxiedPlayer p){
        return main.modList.contains(p.getUniqueId());
    }

    public void removeP(){
        main.players.remove(p.getUniqueId());
        main.modList.remove(p.getUniqueId());
        sendPluginMessage.sendPlayerModActivation(p, getStaffList(p), false);
        p.sendMessage(TextComponent.fromLegacyText(main.staffPrefix + "§cVous n'êtes plus en §cmode §cModérateur §c!"));

    }

    public void init(){
        main.modList.add(p.getUniqueId());
        main.players.put(p.getUniqueId(), this);
        sendPluginMessage.sendPlayerModActivation(p, getStaffList(p), true);
        p.sendMessage(TextComponent.fromLegacyText(main.staffPrefix + "§aVous êtes désormais en §amode §aModérateur §a!"));
    }

    public static ArrayList<UUID> getStaffList(ProxiedPlayer player) {
        ArrayList<UUID> staffList = new ArrayList<>();
        for (ProxiedPlayer players : main.getProxy().getPlayers()) {
            if(hasPermission(players, 13)){
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
