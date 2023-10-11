package fr.edminecoreteam.proxystaff;

import fr.edminecoreteam.proxystaff.commands.*;
import fr.edminecoreteam.proxystaff.edorm.MySQL;
import fr.edminecoreteam.proxystaff.listeners.PlayerSwitch;
import fr.edminecoreteam.proxystaff.listeners.TabCompleteListener;
import fr.edminecoreteam.proxystaff.utils.PlayerManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main extends Plugin {

    private static Main instance;
    public static MySQL database;

    public ArrayList<UUID> modList = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();
    public String staffPrefix = "§d§lSTAFF §8§l» ";
    public ArrayList<UUID> staffList = new ArrayList<>();



    @Override
    public void onEnable() {
        databaseConnect();
        instance = this;
        loadCommands();
        loadListeners();
    }

    @Override
    public void onDisable() {

    }

    private void loadCommands(){
        getProxy().getPluginManager().registerCommand(this, (Command)new CommandStaff(this));
        getProxy().getPluginManager().registerCommand(this, (Command) new CommandMod(this));
        getProxy().getPluginManager().registerCommand(this, new CommandKick(this));
        getProxy().getPluginManager().registerCommand(this, new CommandMute(this));
        getProxy().getPluginManager().registerCommand(this, new CommandNote(this));


    }

    private void loadListeners(){
        getProxy().getPluginManager().registerListener(this, new PlayerSwitch());
        getProxy().getPluginManager().registerListener(this, new TabCompleteListener());
       // getProxy().getPluginManager().registerListener(this, new PlayerJoin());
    }

    private void databaseConnect() {

        (Main.database = new MySQL("jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9")).connexion();
        if (!database.isOnline()) { return; }
        refreshConnexion();

        database.creatingTableMute();
        database.creatingTableKick();
        database.creatingTableNote();
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

    public static Main getInstance(){return instance;}
}
