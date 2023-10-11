package fr.edminecoreteam.proxystaff.commands;

import fr.edminecoreteam.proxystaff.Main;
import fr.edminecoreteam.proxystaff.data.kick.KickInfo;
import fr.edminecoreteam.proxystaff.utils.PlayerManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class CommandKick extends Command {
    private String kick;
    public CommandKick(Main main) {
        super("kick");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = null;

        if(sender instanceof ProxiedPlayer){
            p = (ProxiedPlayer) sender;
        }

        if(PlayerManager.hasPermission(p, 15) || sender instanceof ConsoleCommandSender){
            if(args.length == 0){
                help(sender);
            }else{
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if(target != null){
                    KickInfo kickInfo;
                    if(sender instanceof ConsoleCommandSender){
                        kickInfo = new KickInfo(target.getUniqueId().toString(), sender.getName());
                    }else{
                        kickInfo = new KickInfo(target.getUniqueId().toString(), p.getUniqueId().toString());
                    }
                    ArrayList<UUID> staffList = PlayerManager.getStaffList(p);
                    String reason = null;
                    if(args.length > 1){
                        reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    }
                    if(reason == null){
                        kickInfo.addKick();
                        target.disconnect("§6§LEDMINE §f§lNETWORK \n \n" +
                                "§c§lVous avez été expulsé du serveur. \n \n \n" +
                                "§c⚠ Toutes triches sur nos plateformes sont sanctionnées !");

                        for (UUID staff : staffList){
                            if(staff == p.getUniqueId()){
                                return;
                            }
                            ProxiedPlayer staffP = ProxyServer.getInstance().getPlayer(staff);
                            staffP.sendMessage(TextComponent.fromLegacyText(Main.getInstance().staffPrefix + "§c" + sender.getName() + " §7a expulsé §c" + target.getName() + " §7!"));
                        }
                              //  "§aLe joueur §e" + target.getName() + " §aa été expulsé par §e" + p.getName() + " §e!"));
                    }else{
                        kickInfo.addKick(reason);
                        target.disconnect("§6§LEDMINE §f§lNETWORK \n \n" +
                                "§c§lVous avez été expulsé du serveur. \n \n \n" +
                                "§cInfraction §8§l» §f" + reason + "\n \n \n" +
                                "§c⚠ Toutes triches sur nos plateformes sont sanctionnées !");
                        for(UUID staff : staffList){
                            ProxiedPlayer staffP = ProxyServer.getInstance().getPlayer(staff);
                            if(staffP != sender){
                                staffP.sendMessage(TextComponent.fromLegacyText(Main.getInstance().staffPrefix +  "§c" + sender.getName() + "§7 a expulsé §c" + target.getName() + " §7pour §c" + reason + " §7!"));/*+
                                    "§aLe joueur §e" + target.getName() + " §aa été expulsé par §e" + p.getName() + " §apour : §e" + reason));*/
                            }
                        }
                    }
                    sender.sendMessage(TextComponent.fromLegacyText(Main.getInstance().staffPrefix + "§7Vous avez expulsé §c" + target.getName() + "§7 avec succès !"));

                }else{
                    sender.sendMessage(TextComponent.fromLegacyText(Main.getInstance().staffPrefix + "§cLe joueur §e" + args[0] + " §cn'est pas connecté au serveur !"));
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
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fkick [pseudo] <Raison> §8§l» §7Expulser un joueur du serveur."));
        sender.sendMessage(TextComponent.fromLegacyText(""));
    }
}
