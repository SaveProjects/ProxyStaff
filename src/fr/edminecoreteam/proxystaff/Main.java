package fr.edminecoreteam.proxystaff;

import fr.edminecoreteam.proxystaff.commands.CommandStaff;
import fr.edminecoreteam.proxystaff.edorm.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class Main extends Plugin {

    private static Main instance;
    public static MySQL database;

    @Override
    public void onEnable() {
        loadCommands();
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands(){
        getProxy().getPluginManager().registerCommand(this, (Command)new CommandStaff(this));
    }

    private void databaseConnect() {
        (Main.database = new MySQL("jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9")).connexion();
        if (!database.isOnline()) { return; }
        refreshConnexion();
    }

    public void refreshConnexion() {
        ProxyServer.getInstance().getScheduler().schedule((Plugin) this, (Runnable) new Runnable() {
            @Override
            public void run() {
                if (!Main.database.isOnline()) {
                    Main.database.connexion();
                    run();
                } else {
                    Main.database.deconnexion();
                    Main.database.connexion();
                }
            }
        }, 0L, 120L, TimeUnit.SECONDS);
    }

    public static Main getInstance(){return Main.instance;}
}
