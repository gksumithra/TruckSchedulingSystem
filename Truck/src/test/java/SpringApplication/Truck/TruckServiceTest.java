package SpringApplication.Truck;

import SpringApplication.Truck.AppModel.Truck;
import SpringApplication.Truck.AppRepository.TruckRepository;
import SpringApplication.Truck.AppService.TruckserviceImpl;
import SpringApplication.Truck.Exception.TruckDetailsAlreadyExistsException;
import SpringApplication.Truck.Exception.TruckDetailsNotFoundException;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TruckserviceImpl.class)
public class TruckServiceTest {

    @Autowired
    TruckserviceImpl truckservice;
    @MockBean
    TruckserviceImpl truckservicemock;
    @MockBean
    private TruckRepository truckRepository;

    @Test
    public void testCreateTruck() throws TruckDetailsAlreadyExistsException {
        Truck truck = new Truck(1, "naresh trucks", "flatted Trailer");
        Mockito.when(truckRepository.save(truck)).thenReturn(truck);
        assertThat(truckservice.createTruck(truck)).isEqualTo(truck);
    }

    @Test
    public void testGetAllTrucks() throws Exception {
        Truck truck1 = new Truck(1, "naresh trucks", "flatted Trailer");
        Truck truck2 = new Truck(2,"suresh trucks", "Straight Trailer");
        List<Truck> trucks = new ArrayList<>();
        trucks.add(truck1);
        trucks.add(truck2);
        Mockito.when(truckRepository.findAll()).thenReturn(trucks);
        AssertionsForInterfaceTypes.assertThat(truckservice.getAllTruck()).isEqualTo(trucks);
    }
    @Test
    public void testGetTruckById() throws TruckDetailsNotFoundException {
        when(truckRepository.findById(1L)).thenReturn(Optional.of(new Truck(1, "naresh trucks", "flatted Trailer")));

        ResponseEntity<Truck> truckResponseEntity = truckservice.getTRuckDetailsById(1);

    }
    @Test
    public void testDeleteTruck() throws TruckDetailsNotFoundException {
        Truck truck1 = new Truck(1, "naresh trucks", "flatted Trailer");
        Mockito.doNothing().when(truckservice).deleteTruckDetails(Mockito.any(Integer.class));
        truckservice.deleteTruckDetails(11);
        verify(truckservice, times(1)).deleteTruckDetails(11);
    }


}

