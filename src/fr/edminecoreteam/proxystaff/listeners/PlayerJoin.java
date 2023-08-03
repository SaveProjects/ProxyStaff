package fr.edminecoreteam.proxystaff.listeners;

import fr.edminecoreteam.proxystaff.Main;
import fr.edminecoreteam.proxystaff.utils.PlayerManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(ServerConnectEvent e){
        ProxiedPlayer p = e.getPlayer();
        if(!Main.getInstance().staffList.contains(p.getUniqueId())){
            if(PlayerManager.hasPermission(p, 12)){
            Main.getInstance().staffList.add(p.getUniqueId());
            }
        }
    }
}
