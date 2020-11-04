package SpringApplication.Vendor.AppModel;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@IdClass(VendorId.class)
@Table(name ="vendor")
public class Vendor{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int vendorId;
    private String vendorName;
    @Id
    private String vendorEmailId;
    private long VendorPhoneNumber;
    private String VendorAddress;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmailId() {
        return vendorEmailId;
    }

    public void setVendorEmailId(String vendorEmailId) {
        this.vendorEmailId = vendorEmailId;
    }

    public long getVendorPhoneNumber() {
        return VendorPhoneNumber;
    }

    public void setVendorPhoneNumber(long vendorPhoneNumber) {
        VendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorAddress() {
        return VendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        VendorAddress = vendorAddress;
    }

    public Vendor(int vendorId, String vendorName, String vendorEmailId, long vendorPhoneNumber, String vendorAddress) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorEmailId = vendorEmailId;
        VendorPhoneNumber = vendorPhoneNumber;
        VendorAddress = vendorAddress;
    }

    public Vendor() {
    }
}
