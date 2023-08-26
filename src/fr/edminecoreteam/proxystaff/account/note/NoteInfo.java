package fr.edminecoreteam.proxystaff.account.note;

import java.util.HashMap;
import java.util.Map;

public class NoteInfo {
    private Map<String, NoteInfo> noteInfo;
    private String playerNoted;
    private String playerMod;
    private NoteData noteData;

    public NoteInfo(String playerNoted, String playerMod){
        this.playerNoted = playerNoted;
        this.playerMod = playerMod;
        this.noteInfo = new HashMap<String, NoteInfo>();
        this.noteData = new NoteData(playerNoted, playerMod);
        this.noteInfo.put(playerNoted, this);
        this.noteInfo.put(playerMod, this);
    }

    public void addNote(String note){noteData.addNote(note);}
}
