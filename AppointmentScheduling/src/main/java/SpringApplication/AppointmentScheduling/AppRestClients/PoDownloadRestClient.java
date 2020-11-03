package SpringApplication.AppointmentScheduling.AppRestClients;

import SpringApplication.AppointmentScheduling.AppModel.PoDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="PoDownload-service",url = "http://localhost:8071")

public interface PoDownloadRestClient {
    @GetMapping("/root/podownload/{poNumber}")
    PoDetails getPOById(@PathVariable("poNumber") int poNumber);
}
