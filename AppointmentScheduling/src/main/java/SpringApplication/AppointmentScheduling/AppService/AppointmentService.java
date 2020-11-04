package SpringApplication.AppointmentScheduling.AppService;

import SpringApplication.AppointmentScheduling.AppModel.Appointment;
import SpringApplication.AppointmentScheduling.AppModel.DcSlots;
import SpringApplication.AppointmentScheduling.AppModel.Truck;
import SpringApplication.AppointmentScheduling.AppRepository.AppointmentRepository;
import SpringApplication.AppointmentScheduling.AppRestClients.DcSlotRestClient;
import SpringApplication.AppointmentScheduling.AppRestClients.TruckRestClient;
import SpringApplication.AppointmentScheduling.exception.AppointmentDoesNotExists;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service

public class AppointmentService {
    @Autowired
   private AppointmentRepository appointmentRepository;
    @Autowired
    DcSlotRestClient dcSlotRestClient;
    @Autowired
    TruckRestClient truckRestClient;
    Logger logger = LoggerFactory.getLogger(AppointmentService.class);
    public Appointment scheduleAppointment(Appointment appointment)
    {
        Truck truck=truckRestClient.getTruckDetailsById(appointment.getTruckNumber());

        try {
            appointment.setTruckNumber(appointment.getTruckNumber());
            appointment.setDateOfAppointment(appointment.getDateOfAppointment());
            appointment.setDcNumber(appointment.getDcNumber());
            appointment.setDcSlotNumber(appointment.getDcSlotNumber());
            appointment.setPoNumber(appointment.getPoNumber());
            logger.info(truck.getTruckType());

            return appointmentRepository.save(appointment);
        }
        catch(FeignException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public ResponseEntity<Appointment> getAppointmentByTruckNumber(int truckNumber)
          throws AppointmentDoesNotExists {
        Appointment appointment = appointmentRepository.findById(truckNumber)
                .orElseThrow(() -> new AppointmentDoesNotExists("Appointment not found for this id :: " + truckNumber));
        return ResponseEntity.ok().body(appointment);
    }

    public List<Appointment> getAllAppontments()


    {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    public void cancelAppointment(int truckNumber) throws AppointmentDoesNotExists {
        Appointment appointment = appointmentRepository.findById(truckNumber)
                .orElseThrow(() -> new AppointmentDoesNotExists("Appointment  not found for this truckNumber :: " + truckNumber));

        appointmentRepository.delete(appointment);
    }
}
