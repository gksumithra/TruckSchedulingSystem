package SpringApplication.DcSlots.AppModel;

public class DcSlotDto {
    private long dcNumber;
    private String dcTimeSlots;
    private int maxTruckNumber;
    public long getDcNumber() {
        return dcNumber;
    }

    public void setDcNumber(long dcNumber) {
        this.dcNumber = dcNumber;
    }

    public String getDcTimeSlots() {
        return dcTimeSlots;
    }

    public void setDcTimeSlots(String dcTimeSlots) {
        this.dcTimeSlots = dcTimeSlots;
    }

    public int getMaxTruckNumber() {
        return maxTruckNumber;
    }

    public void setMaxTruckNumber(int maxTruckNumber) {
        this.maxTruckNumber = maxTruckNumber;
    }

    public DcSlotDto() {
    }

    public DcSlotDto(long dcNumber, String dcTimeSlots, int maxTruckNumber) {
        this.dcNumber = dcNumber;
        this.dcTimeSlots = dcTimeSlots;
        this.maxTruckNumber = maxTruckNumber;
    }
}
