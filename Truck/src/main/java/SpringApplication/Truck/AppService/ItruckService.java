package SpringApplication.Truck.AppService;

import SpringApplication.Truck.AppModel.Truck;
import SpringApplication.Truck.Exception.TruckDetailsAlreadyExistsException;
import SpringApplication.Truck.Exception.TruckDetailsNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ItruckService {
    Truck createTruck(Truck truck) throws TruckDetailsAlreadyExistsException;

    Truck updateTruckDetails(long truckNumber, Truck truck) throws TruckDetailsNotFoundException;

    void deleteTruckDetails(long truckNumber) throws TruckDetailsNotFoundException;

    List<Truck> getAllTruck();
    public ResponseEntity<Truck> getTRuckDetailsById(long truckNumber) throws TruckDetailsNotFoundException;
}
