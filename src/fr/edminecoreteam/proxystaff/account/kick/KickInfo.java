package fr.edminecoreteam.proxystaff.account.kick;


import java.util.HashMap;
import java.util.Map;

public class KickInfo {
    private Map<String, KickInfo> kickInfo;
    private String playerKick;
    private String playerMod;
    private KickData kickData;

    public KickInfo(String playerKick){
        this.playerKick = playerKick;
        this.kickInfo = new HashMap<String, KickInfo>();
        this.kickData = new KickData(playerKick);
        this.kickInfo.put(playerKick, this);
    }

    public KickInfo(String playerKick, String playerMod){
        this.playerKick = playerKick;
        this.playerMod = playerMod;
        this.kickInfo = new HashMap<String, KickInfo>();
        this.kickData = new KickData(playerKick, playerMod);
        this.kickInfo.put(playerKick, this);
        this.kickInfo.put(playerMod, this);
    }

    public void addKick(String reason){kickData.addKick(reason);}
    public void addKick(){kickData.addKick();}
    public void getKick(){kickData.getKick();}
}
