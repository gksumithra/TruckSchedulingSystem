package SpringApplication.Truck.AppModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name ="truck")
public class Truck {
    @Id
    private long truckNumber;
    private String truckName;
    private String truckType;

    public Truck(long truckNumber, String truckName, String truckType) {
        this.truckNumber = truckNumber;
        this.truckName = truckName;
        this.truckType = truckType;
    }

    public Truck() {
    }

    public long getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(long truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }
}
