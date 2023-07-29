package fr.edminecoreteam.proxystaff.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

public class sendPluginMessage extends Plugin {

    public static void sendPlayerModActivation(ProxiedPlayer p, ArrayList<UUID> staffList, Boolean modActivation){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ModActivate");
        out.writeUTF(p.getName());


        out.writeInt(staffList.size());

        for(UUID staffP : staffList){
            out.writeUTF(staffP.toString());
        }

        out.writeBoolean(modActivation);

        for(String serverName : ProxyServer.getInstance().getServers().keySet()){
            if(!ProxyServer.getInstance().getServerInfo(serverName).getPlayers().isEmpty()){
                ProxyServer.getInstance().getServerInfo(serverName).sendData("ModActivate", out.toByteArray());
            }
        }

    }
}
