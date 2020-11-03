package SpringApplication.Truck.AppController;

import SpringApplication.Truck.AppModel.Truck;
import SpringApplication.Truck.AppService.ItruckService;
import SpringApplication.Truck.Exception.TruckDetailsAlreadyExistsException;
import SpringApplication.Truck.Exception.TruckDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/root/v1/truck")
public class TruckController {
    @Autowired
    ItruckService itruckService;
    @PostMapping()
    public ResponseEntity<?> createTruck (@RequestBody Truck truck) throws TruckDetailsAlreadyExistsException {
        Truck dc= itruckService.createTruck(truck);
        return ResponseEntity.status(HttpStatus.CREATED).body(dc);
    }
    @GetMapping("/{truckNumber}")
    public ResponseEntity<Truck> getTruckDetailsById(@PathVariable(value="truckNumber") long truckNumber) throws TruckDetailsNotFoundException {
        ResponseEntity<Truck> truck=  itruckService.getTRuckDetailsById(truckNumber);
        return truck;
    }


    @PutMapping("{truckNumber}")
    public ResponseEntity<?> updateTruck(@PathVariable(value = "truckNumber") long truckNumber,@RequestBody Truck truck) throws TruckDetailsNotFoundException {
        return ResponseEntity.ok(itruckService.updateTruckDetails(truckNumber,truck));
    }
    /*@DeleteMapping("/deletedc/{dcnumber}")
    public Map<String, Boolean> deleteDc(@PathVariable(value = "dcNumber") long dcNumber) throws ResourceNotFoundException {
        idcService.deleteDc(dcNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }*/
    @DeleteMapping("{truckNumber}")
    public ResponseEntity<?> deleteTruck(@PathVariable("truckNumber") long truckNumber) throws TruckDetailsNotFoundException {
        itruckService.deleteTruckDetails(truckNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<List<Truck>> getAllTruck(){
        return ResponseEntity.ok(itruckService.getAllTruck());
    }

}
