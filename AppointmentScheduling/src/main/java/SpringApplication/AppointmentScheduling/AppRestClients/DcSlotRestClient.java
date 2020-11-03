package SpringApplication.AppointmentScheduling.AppRestClients;

import SpringApplication.AppointmentScheduling.AppModel.DcSlots;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Dcslot-service",url = "http://localhost:8091")
public interface DcSlotRestClient {
    @GetMapping("/root/v1/dcslots/{dcNumber}")
    DcSlots  getDcSlotDetailsById(@PathVariable("dcNumber") long dcNumber);
}
