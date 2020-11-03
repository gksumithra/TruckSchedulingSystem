package SpringApplication.AppointmentScheduling;

import SpringApplication.AppointmentScheduling.AppController.AppointmentController;
import SpringApplication.AppointmentScheduling.AppModel.Appointment;
import SpringApplication.AppointmentScheduling.AppService.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppointmentController.class)
class AppointmentSchedulingApplicationTests {
	@MockBean
	AppointmentService appointmentService;
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper objectMapper= new ObjectMapper();


	@Test
	public void testCreateAppointment() throws JsonProcessingException, Exception {
		Appointment appointment = new Appointment(101, 1,1, 100,"2020-12-23");
		String URI = "/appointment";
		Mockito.when(appointmentService.scheduleAppointment(Mockito.any(Appointment.class))).thenReturn(appointment);
		mockMvc.perform(post(URI)
				.content(objectMapper.writeValueAsString(appointment))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAllAppointments() throws Exception {
		Appointment appointment1 = new Appointment(101, 1,1, 100,"2020-12-23");
		Appointment appointment2 = new Appointment(102, 2,2, 100,"2020-02-13");
		List<Appointment> appointments = new ArrayList<>();
		appointments.add(appointment1);
		appointments.add(appointment2);
		Mockito.when(appointmentService.getAllAppontments()).thenReturn(appointments);
		String URI = "/appointment";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertEquals(2, appointmentService.getAllAppontments().size());

	}
	@Test
	public void testDeleteAppointment() throws Exception {
		Appointment appointment = new Appointment(101, 1,1, 100,"2020-12-23");
		Mockito.doNothing().when(appointmentService).cancelAppointment(Mockito.any(Integer.class));
		appointmentService.cancelAppointment(101);
		verify(appointmentService, times(1)).cancelAppointment(101);
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.delete("/appointment/101")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	@Test
	public  void testSearchAppointmentById() throws JsonProcessingException, Exception {
		Appointment appointment = new Appointment(101, 1,1, 100,"2020-12-23");
		mockMvc.perform(get("/appointment/{truckNumber}",101)
				.content(objectMapper.writeValueAsString(appointment))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
