package fr.edminecoreteam.proxystaff.listeners;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteListener implements Listener {

    @EventHandler
    public void onTabComplete(TabCompleteEvent e){
        String partialCommand = e.getCursor().toLowerCase();

        if (partialCommand.startsWith("/kick ")) {
            String startName = partialCommand.substring(6); // 6 est la longueur de "/kick " (espace inclus)
            List<String> playerNames = new ArrayList<>();

            for (ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                playerNames.add(players.getName());
            }

            List<String> matchingNames = new ArrayList<>();

            for (String playerName : playerNames) {
                if (playerName.toLowerCase().startsWith(startName)) {
                    matchingNames.add(playerName);
                }
            }
            e.getSuggestions().addAll(matchingNames);
        }
    }
}
