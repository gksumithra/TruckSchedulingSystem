package SpringApplication.Vendor;

import SpringApplication.Vendor.AppController.VendorController;
import SpringApplication.Vendor.AppModel.Vendor;
import SpringApplication.Vendor.AppRepository.VendorRepository;
import SpringApplication.Vendor.AppService.IvendorService;
import SpringApplication.Vendor.AppService.VendorServiceImpl;
import SpringApplication.Vendor.Exception.VendorDetailsAlreadyExistsException;
import SpringApplication.Vendor.Exception.VendorDetailsNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@RunWith(SpringRunner.class)
@WebMvcTest(value = VendorController.class)
class VendorApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VendorServiceImpl vendorService;
    ObjectMapper objectMapper= new ObjectMapper();

    @Test
     void testCreateVendor() throws JsonProcessingException, Exception {
        Vendor vendor = new Vendor(101, "naresh","naresh@gmail.com", 8096789430L,"chennai");
        String URI = "/root/v1/vendor";
        Mockito.when(vendorService.createVendor(Mockito.any(Vendor.class))).thenReturn(vendor);
        mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(vendor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
         void testGetAllVendor() throws Exception {
        Vendor vendor1 = new Vendor(101, "naresh","naresh@gmail.com", 8096789430L,"chennai");
        Vendor vendor2 = new Vendor(102, "suresh","suresh123@gmail.com", 8096712430L,"gudur");
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor1);
        vendors.add(vendor2);
        Mockito.when(vendorService.getAllVendors()).thenReturn(vendors);
        String URI = "/root/v1/vendor";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertEquals(2, vendorService.getAllVendors().size());

    }
    @Test
    void testUpdateVendor() throws JsonProcessingException,Exception {
        Vendor vendor = new Vendor(101, "naresh","naresh@gmail.com", 8096789430L,"chennai");
        Mockito.when(vendorService.updateVendor("naresh@gmail.com",vendor)).thenReturn(vendor);
        mockMvc.perform(put("/root/v1/vendor/naresh@gmail.com")
                .content(objectMapper.writeValueAsString(vendor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
     void testDeleteVendor() throws Exception {
        Vendor vendor = new Vendor(101, "naresh","naresh@gmail.com", 8096789430L,"chennai");
        Mockito.doNothing().when(vendorService).deleteVendor(Mockito.any(String.class));
        vendorService.deleteVendor("naresh@gmail.com");
        verify(vendorService, times(1)).deleteVendor("naresh@gmail.com");
        RequestBuilder requestBuilder =  MockMvcRequestBuilders.delete("/root/v1/vendor/naresh@gmail.com")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }
    @Test
      void testSearchVendorByEmail() throws JsonProcessingException, Exception {
        Vendor vendor = new Vendor(101, "naresh","naresh@gmail.com", 8096789430L,"chennai");
        mockMvc.perform(get("/root/v1/vendor/{vendorEmailId}","naresh@gmail.com")
                .content(objectMapper.writeValueAsString(vendor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

