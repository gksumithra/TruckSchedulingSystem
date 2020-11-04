package SpringApplication.Vendor.AppService;

import java.util.List;

import SpringApplication.Vendor.AppModel.Vendor;
import SpringApplication.Vendor.AppRepository.VendorRepository;
import SpringApplication.Vendor.Exception.VendorDetailsAlreadyExistsException;
import SpringApplication.Vendor.Exception.VendorDetailsNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
@Slf4j
@Service
public class VendorServiceImpl implements IvendorService{
    @Autowired
    VendorRepository vendorRespository;

    String MessageForVendorAlreadyExists;

    @Cacheable(value="vendor",key="vendor.vendorEmailId")
    public Vendor createVendor(Vendor vendor) throws VendorDetailsAlreadyExistsException {
        if(vendorRespository.findByVendorEmailId(vendor.getVendorEmailId()).isPresent())
        {


            throw new VendorDetailsAlreadyExistsException("VendorDetails already Exists for the emailId ::"+ vendor.getVendorEmailId());
        }
        return vendorRespository.save(vendor);
    }
    @Cacheable(value = "vendor",key = "#vendorEmailId")
    public ResponseEntity<Vendor> getVendorByEmail(String vendorEmailId) throws VendorDetailsNotFoundException {

        Vendor vendor = vendorRespository.findByVendorEmailId(vendorEmailId)
                .orElseThrow(() -> new VendorDetailsNotFoundException("VEndor Details not found for this EmailId :: " + vendorEmailId));
        return ResponseEntity.ok().body(vendor);
    }

   @CachePut(value = "vendor",key = "#vendor.vendorEmailId")
   @Override
   public Vendor updateVendor(String vendorEmailId, Vendor vendors) throws VendorDetailsNotFoundException {
       if(vendorRespository.findByVendorEmailId(vendorEmailId).isPresent()) {
           vendors.setVendorName(vendors.getVendorName());
           vendors.setVendorEmailId(vendors.getVendorEmailId());
           vendors.setVendorPhoneNumber(vendors.getVendorPhoneNumber());
           vendors.setVendorAddress(vendors.getVendorAddress());
           final Vendor updatedVendor = vendorRespository.save(vendors);
           return vendorRespository.save(updatedVendor);
       }
       throw new VendorDetailsNotFoundException("Vendor not found for this name :: " + vendorEmailId);
   }


    @CacheEvict(value = "vendor",key = "#vendorEmailId")
    @Override
    public void deleteVendor(@PathVariable(value = "name") String vendorEmailId)
            throws VendorDetailsNotFoundException {
        Vendor vendor =((VendorRepository) vendorRespository).findByVendorEmailId(vendorEmailId)
                .orElseThrow(() -> new VendorDetailsNotFoundException("Vendor not found for this name :: " + vendorEmailId));

        vendorRespository.delete(vendor);

    }
    @Cacheable(value = "vendor")
    public List<Vendor> getAllVendors(){
        return  (List<Vendor>) vendorRespository.findAll();
    }

}


