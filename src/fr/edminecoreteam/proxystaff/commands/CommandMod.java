package fr.edminecoreteam.proxystaff.commands;

import fr.edminecoreteam.proxystaff.Main;
import fr.edminecoreteam.proxystaff.utils.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandMod extends Command {
    private String mod;

    public CommandMod(Main main) {
        super("mod");
        this.mod = "mod";
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if(PlayerManager.hasPermission(p)){
                if(PlayerManager.isInModerationMod(p)){
                    PlayerManager pm = new PlayerManager(p);
                    pm.removeP();
                }else{
                    PlayerManager pm = new PlayerManager(p);
                    pm.init();
                }
            }else{
                p.sendMessage(TextComponent.fromLegacyText("§cErreur, vous n'avez pas la permission..."));
            }
        }
    }
}
