package fr.edminecoreteam.proxystaff.commands;

import fr.edminecoreteam.proxystaff.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;


public class CommandStaff extends Command {
    private String staff;

    public CommandStaff(Main main) {
        super("staff", "edmine.admin");
        this.staff = "staff";
    }

    @Override
    public void execute(CommandSender sender, String[] args){
        if(args.length == 0){
            help(sender);
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("help")){
                help(sender);
            }
            if (args[0].equalsIgnoreCase("list")){

            }
            if(args[0].equalsIgnoreCase("cmd")){
                cmd(sender);
            }
        }
    }


    private void help(CommandSender sender){
        sender.sendMessage(TextComponent.fromLegacyText(""));
        sender.sendMessage(TextComponent.fromLegacyText(" §7» §6§lCentre d'aide §6(EDMINE STAFF):"));
        sender.sendMessage(TextComponent.fromLegacyText(""));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fstaff help §8§l» §7Afficher l'aide."));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fstaff list §8§l» §7Afficher le staff connecté."));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fstaff cmd §8§l» §7Afficher la liste de vos commandes."));
        sender.sendMessage(TextComponent.fromLegacyText(""));
    }

    private void cmd(CommandSender sender){
        sender.sendMessage(TextComponent.fromLegacyText(""));
        sender.sendMessage(TextComponent.fromLegacyText(" §7» §6§lCentre d'aide §6(EDMINE STAFF):"));
        sender.sendMessage(TextComponent.fromLegacyText(""));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fstaff help §8§l» §7Afficher l'aide."));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fstaff list §8§l» §7Afficher le staff connecté."));
        sender.sendMessage(TextComponent.fromLegacyText(" §7• §d/§fstaff cmd §8§l» §7Afficher la liste de vos commandes."));
        sender.sendMessage(TextComponent.fromLegacyText(""));
    }
}
