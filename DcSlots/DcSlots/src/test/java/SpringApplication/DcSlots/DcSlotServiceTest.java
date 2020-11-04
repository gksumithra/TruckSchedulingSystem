package SpringApplication.DcSlots;

import SpringApplication.DcSlots.AppModel.DcSlots;
import SpringApplication.DcSlots.AppRepository.DcSlotRepository;
import SpringApplication.DcSlots.AppService.DcSlotServiceImpl;
import SpringApplication.DcSlots.exception.DcSlotAlreadyExistsException;
import SpringApplication.DcSlots.exception.DcSlotNotFoundException;
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
@WebMvcTest(value = DcSlotServiceImpl.class)
class DcSlotServiceTest {

	@Autowired
	DcSlotServiceImpl dcService;
	@MockBean
	private DcSlotRepository dcSlotRepository;
	@Test
	 void testCreateDcSlot() throws DcSlotAlreadyExistsException {
		DcSlots dcSlots = new DcSlots(1, "4",10);
		Mockito.when(dcSlotRepository.save(dcSlots)).thenReturn(dcSlots);
		assertThat(dcService.createDcSlots(dcSlots)).isEqualTo(dcSlots);
	}

	@Test
	 void testGetAllDcSlots() throws Exception {
		DcSlots dcSlot1 = new DcSlots(1, "4",10);
		DcSlots dcSlot2 = new DcSlots(2, "5",10);
		List<DcSlots> dcSlots = new ArrayList<>();
		dcSlots.add(dcSlot1);
		dcSlots.add(dcSlot2);
		Mockito.when(dcSlotRepository.findAll()).thenReturn(dcSlots);
		AssertionsForInterfaceTypes.assertThat(dcService.getAllDcSlots()).isEqualTo(dcSlots);
	}
	@Test
	 void testGetDcSlotById() throws  DcSlotNotFoundException {
		DcSlots dcSlots= new DcSlots(1, "4",10);
		when(dcSlotRepository.findById(1L)).thenReturn(Optional.of(dcSlots));
		ResponseEntity<DcSlots> dc = dcService.getDcSlotById(1L);
		verify(dcService, times(1)).getDcSlotById(11L);

	}
	@Test
	void testUpdateDcSlot() throws DcSlotNotFoundException {
		DcSlots dcslot = new DcSlots(1L, "7", 5);
		Mockito.when(dcSlotRepository.save(dcslot)).thenReturn(dcslot);
		assertThat(dcService.updateDcSlot(1L, dcslot)).isEqualTo(dcslot);

	}

	@Test
	void testDeleteDcSlot() throws DcSlotNotFoundException {
		DcSlots dcslot = new DcSlots(3, "5", 15);
		Mockito.doNothing().when(dcService).deleteDcSlot(Mockito.any(Integer.class));
		dcService.deleteDcSlot(11);
		verify(dcService, times(1)).deleteDcSlot(11);
	}

}
