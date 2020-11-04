package SpringApplication.Vendor.AppRepository;

import SpringApplication.Vendor.AppModel.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface VendorRepository extends CrudRepository<Vendor,String> {
  Optional<Vendor> findByVendorEmailId(String vendorEmailId);

}


