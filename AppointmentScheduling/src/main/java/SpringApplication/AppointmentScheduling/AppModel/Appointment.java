package SpringApplication.AppointmentScheduling.AppModel;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    private int truckNumber;
    private long dcNumber;
    private long dcSlotNumber;
    private int poNumber;
    private String dateOfAppointment;

    public Appointment(int truckNumber, long dcNumber, long dcSlotNumber, int poNumber, String dateOfAppointment) {
        this.truckNumber = truckNumber;
        this.dcNumber = dcNumber;
        this.dcSlotNumber = dcSlotNumber;
        this.poNumber = poNumber;
        this.dateOfAppointment = dateOfAppointment;
    }

    public Appointment() {
    }

    public int getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(int truckNumber) {
        this.truckNumber = truckNumber;
    }

    public long getDcNumber() {
        return dcNumber;
    }

    public void setDcNumber(long dcNumber) {
        this.dcNumber = dcNumber;
    }

    public long getDcSlotNumber() {
        return dcSlotNumber;
    }

    public void setDcSlotNumber(long dcSlotNumber) {
        this.dcSlotNumber = dcSlotNumber;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }
}
