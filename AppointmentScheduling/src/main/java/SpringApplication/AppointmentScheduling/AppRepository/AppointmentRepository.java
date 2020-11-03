package SpringApplication.AppointmentScheduling.AppRepository;

import SpringApplication.AppointmentScheduling.AppModel.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment,Integer> {
}
