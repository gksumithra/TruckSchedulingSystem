package SpringApplication.DcSlots.AppModel;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TimeSlot {
    public String dcSlotMethod(int hours, int minutes) {
        LocalTime time1 = LocalTime.of(hours, minutes);
        LocalTime time2 = LocalTime.of(1,0);
        LocalTime result = time2.plusHours(hours).plusMinutes(minutes);
        String initialTime = time1.toString();
        String finalTime = result.toString();
        String time = initialTime.concat("-").concat(finalTime);
        return time;
    }
}