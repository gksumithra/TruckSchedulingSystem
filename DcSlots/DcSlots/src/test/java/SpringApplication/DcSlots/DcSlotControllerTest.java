package SpringApplication.DcSlots;

import SpringApplication.DcSlots.AppController.DcSlotsController;
import SpringApplication.DcSlots.AppModel.DcSlots;
import SpringApplication.DcSlots.AppService.DcSlotServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
@WebMvcTest(value = DcSlotsController.class)
@RunWith(SpringRunner.class)
public class DcSlotControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DcSlotServiceImpl dcSlotService;
    ObjectMapper objectMapper= new ObjectMapper();

    @Test
     void testCreateDcSlot() throws JsonProcessingException, Exception {
        DcSlots dcSlots = new DcSlots(2211, "6", 10);
        String URI = "/root/v1/dcslots";
        Mockito.when(dcSlotService.createDcSlots(Mockito.any(DcSlots.class))).thenReturn(dcSlots);
        mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(dcSlots))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
     void testGetAllDcSlot() throws Exception {
        DcSlots dcSlots = new DcSlots(12, "6", 6);
        DcSlots dcSlots1 = new DcSlots(11, "7", 10);
        List<DcSlots> dc = new ArrayList<>();
        dc.add(dcSlots);
        dc.add(dcSlots1);
        Mockito.when(dcSlotService.getAllDcSlots()).thenReturn(dc);
        String URI = "/root/v1/dcslots";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertEquals(2, dcSlotService.getAllDcSlots().size());

    }
    @Test
     void testUpdateDcSlot() throws JsonProcessingException,Exception {
        DcSlots dc= new DcSlots(11,"6",10);
        Mockito.when(dcSlotService.updateDcSlot(11L,dc)).thenReturn(dc);
        mockMvc.perform(put("/root/v1/dcslots/11")
                .content(objectMapper.writeValueAsString(dc))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
     void testDeleteDcSlot() throws Exception {
        DcSlots dc= new DcSlots(11,"6",5);
        Mockito.doNothing().when(dcSlotService).deleteDcSlot(Mockito.any(Integer.class));
        dcSlotService.deleteDcSlot(11);
        verify(dcSlotService, times(1)).deleteDcSlot(11);
        RequestBuilder requestBuilder =  MockMvcRequestBuilders.delete("/root/v1/dcslots/11")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }
    @Test
      void testSearchDcSlotById() throws JsonProcessingException, Exception {
        DcSlots dcslots= new DcSlots(11,"9",12);
        mockMvc.perform(get("/root/v1/dcslots/{dcNumber}",11)
                .content(objectMapper.writeValueAsString(dcslots))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
