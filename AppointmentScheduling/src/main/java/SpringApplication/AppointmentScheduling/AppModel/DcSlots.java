package SpringApplication.AppointmentScheduling.AppModel;


import javax.persistence.Column;
public class DcSlots {

    private long dcNumber;
    private String dcTimeSlots;
    private int maxTruckNumber;

    public long getDcNumber() {
        return dcNumber;
    }

    public void setDcNumber(long dcNumber) {
        this.dcNumber = dcNumber;
    }
    @Column(name = "dcTimeSlots", nullable = false)
    public String getDcTimeSlots() {
        return dcTimeSlots;
    }

    public void setDcTimeSlots(String dcTimeSlots) {
        this.dcTimeSlots = dcTimeSlots;
    }
    @Column(name = "maxTruckNumber", nullable = false)
    public int getMaxTruckNumber() {
        return maxTruckNumber;
    }

    public void setMaxTruckNumber(int maxTruckNumber) {
        this.maxTruckNumber = maxTruckNumber;
    }

    public DcSlots() {
    }

    public DcSlots(long dcNumber, String dcTimeSlots, int maxTruckNumber) {
        this.dcNumber = dcNumber;
        this.dcTimeSlots = dcTimeSlots;
        this.maxTruckNumber = maxTruckNumber;
    }
}
