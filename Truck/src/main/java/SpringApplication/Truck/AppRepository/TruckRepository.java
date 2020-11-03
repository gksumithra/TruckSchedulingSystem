package SpringApplication.Truck.AppRepository;

import SpringApplication.Truck.AppModel.Truck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends CrudRepository<Truck,Long> {
}
