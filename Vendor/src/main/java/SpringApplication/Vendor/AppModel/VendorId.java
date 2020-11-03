package SpringApplication.Vendor.AppModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VendorId implements Serializable {
    private int vendorId;
    private String vendorEmailId;
}
