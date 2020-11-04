package spring.application.DistributionCenter;

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
import spring.application.DistributionCenter.AppController.DcController;
import spring.application.DistributionCenter.AppModel.DistributionCenter;
import spring.application.DistributionCenter.AppService.DcServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DcController.class)
public class dcControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DcServiceImpl dcService;
    ObjectMapper objectMapper= new ObjectMapper();

   @Test
 void testCreateDc() throws JsonProcessingException, Exception{
       DistributionCenter distributionCenter=new DistributionCenter(2211,"tiruthani","international");
       String URI="/root/v1/dc";
       Mockito.when(dcService.createDc(Mockito.any(DistributionCenter.class))).thenReturn(distributionCenter);
       mockMvc.perform(post(URI)
               .content((objectMapper.writeValueAsString(distributionCenter)))
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
   }

   @Test
  void testGetAllDc() throws Exception {
       DistributionCenter distributionCenter=new DistributionCenter(12,"gudur","international");
       DistributionCenter distributionCenter1=new DistributionCenter(11,"nellore","regional");
       List<DistributionCenter> dc= new ArrayList<>();
       dc.add(distributionCenter);
       dc.add(distributionCenter1);
       Mockito.when(dcService.getAllDcs()).thenReturn(dc);
       String URI="/root/v1/dc";
       RequestBuilder requestBuilder= get(URI);
       MvcResult result= mockMvc.perform(requestBuilder).andReturn();
       MockHttpServletResponse response=result.getResponse();
       String outputInJson =response.getContentAsString();
       assertEquals(2,dcService.getAllDcs().size()); Mockito.doNothing().when(dcService).deleteDc(Mockito.any(Integer.class));
       dcService.deleteDc(11);
       verify(dcService, times(1)).deleteDc(11);

   }
   @Test
   void testUpdateDc() throws JsonProcessingException,Exception {
       DistributionCenter dc= new DistributionCenter(11,"adhoni","intermational");
      Mockito.when(dcService.updateDc(11,dc)).thenReturn(dc);
       mockMvc.perform(put("/root/v1/dc/11")
                .content(objectMapper.writeValueAsString(dc))
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
   }
    @Test
  void testDeleteDc() throws Exception {
        DistributionCenter dc= new DistributionCenter(11,"adhoni","intermational");

        RequestBuilder requestBuilder =  MockMvcRequestBuilders.delete("/root/v1/dc/11")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
   void testSearchDcById() throws JsonProcessingException, Exception {
        DistributionCenter dc= new DistributionCenter(11,"gudur","Regional");
        mockMvc.perform(get("/root/v1/dc/{dcNumber}",11)
                .content(objectMapper.writeValueAsString(dc))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
