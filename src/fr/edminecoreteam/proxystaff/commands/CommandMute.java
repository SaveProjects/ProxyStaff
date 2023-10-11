package fr.edminecoreteam.proxystaff.commands;

import fr.edminecoreteam.proxystaff.Main;
import fr.edminecoreteam.proxystaff.data.uuid.UUIDInfo;
import fr.edminecoreteam.proxystaff.utils.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class CommandMute extends Command {
    public CommandMute(Main main) {
        super("mute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = null;
        if(sender instanceof ProxiedPlayer){
            p = (ProxiedPlayer) sender;
        }
        if(PlayerManager.hasPermission(p, 13) || sender instanceof ConsoleCommandSender){
            if(args.length == 0){
                help(sender);
            }else{
                String targetUUID = null;

                UUIDInfo uuidInfo = new UUIDInfo(args[0]);
                targetUUID = uuidInfo.getUUID();

                if(targetUUID == null){
                    sender.sendMessage(TextComponent.fromLegacyText(Main.getInstance().staffPrefix + "§cLe joueur §e" + args[0] + " §cn'existe pas sur le serveur !"));
                }else{
                    sender.sendMessage(TextComponent.fromLegacyText(String.valueOf(targetUUID)));
                }

            }
        }else{
            sender.sendMessage(TextComponent.fromLegacyText("§cCommande inconnue..."));
        }
    }

    private void help(CommandSender sender){
        sender.sendMessage(TextComponent.fromLegacyText(""));
        sender.sendMessage(TextComponent.fromLegacyText(" §7» §6§lCentre d'aide §6(EDMINE STAFF):"));
        sender.sendMessage(TextComponent.fromLegacyText(""));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fmute [Pseudo] <Temps> <Raison> §8§l» §7Mute un joueur."));
        sender.sendMessage(TextComponent.fromLegacyText(""));
    }
}
