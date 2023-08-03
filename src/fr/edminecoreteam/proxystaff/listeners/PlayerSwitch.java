package fr.edminecoreteam.proxystaff.listeners;

import fr.edminecoreteam.proxystaff.utils.PlayerManager;
import fr.edminecoreteam.proxystaff.utils.sendPluginMessage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class PlayerSwitch implements Listener {

    @EventHandler
    public void onPlayerSwitch(ServerConnectedEvent e){
        ProxiedPlayer p = e.getPlayer();
        String server = e.getServer().getInfo().getName();

        if(PlayerManager.isInModerationMod(p)){
            sendPluginMessage.sendPlayerModIsActivate(p, PlayerManager.getStaffList(p), server);
        }
    }
}
