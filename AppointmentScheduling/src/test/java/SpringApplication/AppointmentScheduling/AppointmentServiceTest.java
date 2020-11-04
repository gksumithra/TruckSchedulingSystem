package SpringApplication.AppointmentScheduling;

import SpringApplication.AppointmentScheduling.AppModel.Appointment;
import SpringApplication.AppointmentScheduling.AppRepository.AppointmentRepository;
import SpringApplication.AppointmentScheduling.AppService.AppointmentService;
import SpringApplication.AppointmentScheduling.exception.AppointmentDoesNotExists;
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
@WebMvcTest(value = AppointmentService.class)
public class AppointmentServiceTest {

    @Autowired
    AppointmentService appointmentService;
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Test
    public void testScheduleAppointment() {
        Appointment appointment = new Appointment(101,1,101,1001,"2020-04-12");
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        assertThat(appointmentService.scheduleAppointment(appointment)).isEqualTo(appointment);
    }
    @Test
    public void testGetAllAppointmentDetails() throws Exception {
        Appointment appointment1 = new Appointment(101,1,101,1001,"2020-04-12");
        Appointment appointment2 = new Appointment(102,2,102,1002,"2020-05-20");
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);
        Mockito.when(appointmentRepository.findAll()).thenReturn(appointments);
        AssertionsForInterfaceTypes.assertThat(appointmentService.getAllAppontments()).isEqualTo(appointments);
    }
    @Test
    public void testGetAppointmentById() throws AppointmentDoesNotExists {
        when(appointmentRepository.findById(1)).thenReturn(Optional.of(new Appointment(101,1,101,1001,"2020-04-12")));

        ResponseEntity<Appointment> appointmentResponseEntity = appointmentService.getAppointmentByTruckNumber(1);
        verify(appointmentService, times(1)).getAppointmentByTruckNumber(11);

    }
}


