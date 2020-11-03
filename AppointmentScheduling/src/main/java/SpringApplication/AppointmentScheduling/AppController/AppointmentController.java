package SpringApplication.AppointmentScheduling.AppController;

import SpringApplication.AppointmentScheduling.AppModel.Appointment;
import SpringApplication.AppointmentScheduling.AppService.AppointmentService;
import SpringApplication.AppointmentScheduling.exception.AppointmentDoesNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

@Autowired
   private AppointmentService appointmentService;
    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppontments();
    }
    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment)
    {
        appointmentService.scheduleAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
    }
    @GetMapping("/{truckNumber}")
    public ResponseEntity<Appointment> getAppointmentByTruckNumber(@PathVariable(value="truckNumber")int truckNumber) throws AppointmentDoesNotExists {
        return appointmentService.getAppointmentByTruckNumber(truckNumber);
    }
    @DeleteMapping("/{truckNumber}")
    public ResponseEntity<?> cancelAppointment(@PathVariable("truckNumber") int truckNumber) throws AppointmentDoesNotExists{
        appointmentService.cancelAppointment(truckNumber);
        return ResponseEntity.ok().build();
    }

}
