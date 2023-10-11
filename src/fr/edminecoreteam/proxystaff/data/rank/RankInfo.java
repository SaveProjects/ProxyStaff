package fr.edminecoreteam.proxystaff.data.rank;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;

public class RankInfo {
    private Map<ProxiedPlayer, RankInfo> rankInfo;
    private ProxiedPlayer p;
    private RankData rankData;

    public RankInfo(ProxiedPlayer p){
        this.p = p;
        this.rankInfo = new HashMap<ProxiedPlayer, RankInfo>();
        this.rankData = new RankData(p);
        this.rankInfo.put(p, this);
    }


    public RankInfo getAccountInfos(ProxiedPlayer player) { return rankInfo.get(player); }

    public String getPlayerName() { return p.getName(); }

    public boolean hasRank() { return rankData.hasRank(); }

    public void updateRankName(String name) { rankData.updateRankName(name); }

    public int getRankID() { return rankData.getRankID(); }

    public int getRankModule() { return rankData.getRankModule(); }


    public String getRankType() { return rankData.getRankType(); }
}
