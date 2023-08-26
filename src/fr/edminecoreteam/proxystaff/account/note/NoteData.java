package fr.edminecoreteam.proxystaff.account.note;

import fr.edminecoreteam.proxystaff.edorm.MySQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoteData {
    protected static String table;
    private static String playerNoted;
    private static String playerMod;
    public NoteData(String playerNoted, String playerMod){
        this.playerNoted = playerNoted;
        this.playerMod = playerMod;
    }
    static {
        NoteData.table = "ed_note";
    }

    public void addNote(String note){
        try{
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO " + table + " (player_uuid, note_text, note_by_uuid) VALUES (?, ?, ?)");
            ps.setString(1, playerNoted);
            ps.setString(2, note);
            ps.setString(3, playerMod);
            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
