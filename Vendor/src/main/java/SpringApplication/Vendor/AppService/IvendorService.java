package SpringApplication.Vendor.AppService;

import SpringApplication.Vendor.AppModel.Vendor;
import SpringApplication.Vendor.Exception.VendorDetailsAlreadyExistsException;
import SpringApplication.Vendor.Exception.VendorDetailsNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IvendorService {
    public Vendor createVendor(Vendor vendor) throws VendorDetailsAlreadyExistsException;
    public void deleteVendor(String vendorEmailId) throws VendorDetailsNotFoundException;
    public Vendor updateVendor(String vendorEmailId, Vendor vendorDetails) throws VendorDetailsNotFoundException;
    public List<Vendor> getAllVendors();

    ResponseEntity<Vendor> getVendorByEmail(String vendorEmailId) throws VendorDetailsNotFoundException;
}
