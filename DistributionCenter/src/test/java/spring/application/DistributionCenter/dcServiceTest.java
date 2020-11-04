package spring.application.DistributionCenter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import spring.application.DistributionCenter.AppModel.DistributionCenter;
import spring.application.DistributionCenter.AppRepository.DcRespository;
import spring.application.DistributionCenter.AppService.DcServiceImpl;
import spring.application.DistributionCenter.exception.DcAlreadyExistsException;
import spring.application.DistributionCenter.exception.DcNotFoundException;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DcServiceImpl.class)
class dcServiceTest {
	@Autowired
	DcServiceImpl dcService;
	@MockBean
	DcServiceImpl dcServicemock;
	@MockBean
	private DcRespository dcRespository;
	@Test
	 void testCreateDc() throws DcAlreadyExistsException {
		DistributionCenter dc = new DistributionCenter(1, "nellore", "international");
		Mockito.when(dcRespository.save(dc)).thenReturn(dc);
		assertThat(dcService.createDc(dc)).isEqualTo(dc);
	}

	@Test
	 void testGetAllDcs() throws Exception {
		DistributionCenter dc1 = new DistributionCenter(1, "nellore", "international");
		DistributionCenter dc2 = new DistributionCenter(2, "Gudur", "regional");
		List<DistributionCenter> dc = new ArrayList<>();
		dc.add(dc1);
		dc.add(dc2);
		Mockito.when(dcRespository.findAll()).thenReturn(dc);
		assertThat(dcService.getAllDcs()).isEqualTo(dc);
	}

	@Test
	 void testGetDcById() throws DcNotFoundException {
		when(dcRespository.findById(11L)).thenReturn(Optional.of(new DistributionCenter(11L, "nellore", "international")));

		ResponseEntity<DistributionCenter> dc = dcService.getDcById(11L);

		verify(dcService, times(1)).getDcById(11L);
	}

	@Test
	 void testUpdateDc() throws DcNotFoundException {
		DistributionCenter dc = new DistributionCenter(1L, "nellore", "international");
		Mockito.when(dcRespository.save(dc)).thenReturn(dc);
		assertThat(dcService.updateDc(1L, dc)).isEqualTo(dc);

	}

	@Test
	 void testDeleteDc() throws DcNotFoundException {
		DistributionCenter dc1 = new DistributionCenter(1L ,"nellore", "international");
		Mockito.doNothing().when(dcService).deleteDc(Mockito.any(Integer.class));
		dcService.deleteDc(1L);
		verify(dcService, times(1)).deleteDc(1L);
	}
}

