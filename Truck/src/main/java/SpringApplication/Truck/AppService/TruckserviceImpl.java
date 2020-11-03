package SpringApplication.Truck.AppService;

import SpringApplication.Truck.AppModel.Truck;
import SpringApplication.Truck.AppRepository.TruckRepository;
import SpringApplication.Truck.Exception.TruckDetailsAlreadyExistsException;
import SpringApplication.Truck.Exception.TruckDetailsNotFoundException;
import lombok.extern.slf4j.Slf4j;
//import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class TruckserviceImpl implements ItruckService {


    @Autowired
    TruckRepository truckRepository;
    private String exceptionMsgForBlogAlreadyAvailable;

    private String exceptionMsgForBlogNotFound;
//    @Cacheable(value="truck",key="truck.truckNumber")
    @Override
    public Truck createTruck(Truck truck) throws TruckDetailsAlreadyExistsException {
        if(truckRepository.findById(truck.getTruckNumber()).isPresent())
        {

            throw new TruckDetailsAlreadyExistsException(exceptionMsgForBlogAlreadyAvailable);
        }
        return truckRepository.save(truck);
    }

    /*@Cacheable(value = "truck",key = "#truckNumber")*/
    @Override
    public ResponseEntity<Truck> getTRuckDetailsById(long truckNumber) throws TruckDetailsNotFoundException {
        Truck truck = truckRepository.findById(truckNumber)
                .orElseThrow(() -> new TruckDetailsNotFoundException("Truck Details not found for this TruckNumber :: " + truckNumber));
        return ResponseEntity.ok().body(truck);

    }
   /* @CachePut(value = "truck",key = "#truck.truckNumber")*/
    @Override
    public Truck updateTruckDetails(long truckNumber, Truck truck) throws TruckDetailsNotFoundException {
        if(truckRepository.findById(truckNumber).isPresent()) {
            truck.setTruckNumber(truck.getTruckNumber());
            truck.setTruckName(truck.getTruckName());
            truck.setTruckType(truck.getTruckType());
            final Truck updatedTruck = truckRepository.save(truck);
            return truckRepository.save(updatedTruck);
        }
        throw new TruckDetailsNotFoundException("Truck not found for this TruckId :: " + truckNumber);
    }
   // @CacheEvict(value = "truck",key = "#truckNumber")
    @Override
    public void deleteTruckDetails(long truckNumber)
            throws TruckDetailsNotFoundException {
        Truck truck = truckRepository.findById(truckNumber).orElseThrow(() -> new TruckDetailsNotFoundException("Truck  not found for this truckNumber :: " + truckNumber));

        truckRepository.delete(truck);

    }
    /*@Cacheable(value = "truck")*/
    @Override
    public List<Truck> getAllTruck() {
        return (List<Truck>) truckRepository.findAll();
    }

}

