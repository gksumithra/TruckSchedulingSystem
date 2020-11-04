package SpringApplication.Vendor.AppController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SpringApplication.Vendor.AppModel.Vendor;
import SpringApplication.Vendor.AppService.IvendorService;
import SpringApplication.Vendor.Exception.VendorDetailsAlreadyExistsException;
import SpringApplication.Vendor.Exception.VendorDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/root/v1/vendor")
public class VendorController {
    @Autowired
    private IvendorService ivendorService;

    @PostMapping
    public ResponseEntity<Vendor> createVendor (@RequestBody Vendor vendor) throws VendorDetailsAlreadyExistsException {
        Vendor vendorNew= ivendorService.createVendor(vendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorNew);
    }

    @GetMapping("/{vendorEmailId}")
    public ResponseEntity<Vendor> getVendorDetailsByEmail(@PathVariable(value="vendorEmailId") String vendorEmailId) throws VendorDetailsNotFoundException {
        ResponseEntity<Vendor> vendor=  ivendorService.getVendorByEmail(vendorEmailId);
        return vendor;
    }

    @PutMapping("/{vendorEmailId}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable(value = "vendorEmailId") String vendorEmailId,@RequestBody Vendor vendor) throws VendorDetailsNotFoundException {
        return ResponseEntity.ok(ivendorService.updateVendor(vendorEmailId,vendor));
    }
    @DeleteMapping("/{vendorEmailId}")
    public Map<String, Boolean> deleteVendor(@PathVariable(value = "vendorEmailId") String vendorEmailId) throws VendorDetailsNotFoundException {
        ivendorService.deleteVendor(vendorEmailId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors(){
        return ResponseEntity.ok(ivendorService.getAllVendors());
    }

}