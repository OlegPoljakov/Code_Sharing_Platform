package platform;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Date {

    public static String getDate() {
        LocalDateTime nowdate = LocalDateTime.now();
        LocalTime nowtime = LocalTime.now();
        String formattedDate = nowdate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " " + nowtime.withNano(0);
        return formattedDate;
    }

    public static String getNewUUID(){
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
