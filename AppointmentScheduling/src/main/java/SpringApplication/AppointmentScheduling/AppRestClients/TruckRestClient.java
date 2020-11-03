package SpringApplication.AppointmentScheduling.AppRestClients;

import SpringApplication.AppointmentScheduling.AppModel.Truck;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Truck-service",url = "http://localhost:8093")
public interface TruckRestClient {
    @GetMapping("/root/v1/truck/{truckNumber}")
    Truck getTruckDetailsById(@PathVariable("truckNumber") long truckNumber);
}
