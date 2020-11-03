package SpringApplication.Truck;

import SpringApplication.Truck.AppController.TruckController;
import SpringApplication.Truck.AppModel.Truck;
import SpringApplication.Truck.AppService.TruckserviceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(value = TruckController.class)
class TruckApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TruckserviceImpl truckservice;
	ObjectMapper objectMapper= new ObjectMapper();

	@Test
	public void testCreateTruck() throws JsonProcessingException, Exception {
		Truck truck = new Truck(101, "naresh trucks", "flatted");
		String URI = "/root/v1/truck";
		Mockito.when(truckservice.createTruck(Mockito.any(Truck.class))).thenReturn(truck);
		mockMvc.perform(post(URI)
				.content(objectMapper.writeValueAsString(truck))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAllTrucks() throws Exception {
		Truck truck1= new Truck(101, "naresh trucks", "flatted");
		Truck truck2 = new  Truck(102, "sankar trucks", "Straight truck");
		List<Truck> trucks = new ArrayList<>();
		trucks.add(truck1);
		trucks.add(truck2);
		Mockito.when(truckservice.getAllTruck()).thenReturn(trucks);
		String URI = "/root/v1/trucks";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertEquals(2, truckservice.getAllTruck().size());

	}
	@Test
	public void testUpdateTruck() throws JsonProcessingException,Exception {
		Truck truck= new Truck(101, "naresh trucks", "flatted");
		Mockito.when(truckservice.updateTruckDetails(101,truck)).thenReturn(truck);
		mockMvc.perform(put("/root/v1/truck/101")
				.content(objectMapper.writeValueAsString(truck))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void testDeleteTruck() throws Exception {
		Truck truck= new Truck(101, "naresh trucks", "flatted");
		Mockito.doNothing().when(truckservice).deleteTruckDetails(Mockito.any(Integer.class));
		truckservice.deleteTruckDetails(11);
		verify(truckservice, times(1)).deleteTruckDetails(11);
		RequestBuilder requestBuilder =  MockMvcRequestBuilders.delete("/root/v1/truck/11")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	@Test
	public  void testSearchDcSlotById() throws JsonProcessingException, Exception {
		Truck truck= new Truck(101, "naresh trucks", "flatted");
		mockMvc.perform(get("/root/v1/truck/{truckNumber}",101)
				.content(objectMapper.writeValueAsString(truck))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
