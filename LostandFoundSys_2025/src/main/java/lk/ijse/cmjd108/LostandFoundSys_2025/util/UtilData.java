package lk.ijse.cmjd108.LostandFoundSys_2025.util;

import java.util.UUID;

public class UtilData {
    public static String generateItemId(){
        return "ITM-" + UUID.randomUUID().toString();
    }

    public static String generateUserId(){
        return "USR-" + UUID.randomUUID().toString();
    }

    public static String generateRequestId(){
        return "REQ-" + UUID.randomUUID().toString();
    }
}
