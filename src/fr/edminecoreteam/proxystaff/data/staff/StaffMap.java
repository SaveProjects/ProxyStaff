package fr.edminecoreteam.proxystaff.data.staff;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class StaffMap {
    private static HashMap<UUID, Integer> staffList;

    public static void addStaff(UUID pUUID, Integer permLevel){
        staffList.put(pUUID, permLevel);
    }

    public static Integer getStaffLevel(UUID pUUID){
        return staffList.get(pUUID);
    }

    public static ArrayList<UUID> getStaffList(){
        ArrayList<UUID> staffs = new ArrayList<>();
        for(UUID staff : staffList.keySet()){
            staffs.add(staff);
        }
        return staffs;
    }

    public static boolean isStaff(UUID pUUID){
        if(staffList.containsKey(pUUID)){
            return true;
        }else{
            return false;
        }
    }


}
