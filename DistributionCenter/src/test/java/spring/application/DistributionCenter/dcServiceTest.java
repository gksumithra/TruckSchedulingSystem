package spring.application.DistributionCenter;

import org.junit.Assert;
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
	private DcRespository dcRespository;
	/*@MockBean
	DcServiceImpl dcServicemock;*/
	@Test
	public void testCreateDc() throws DcAlreadyExistsException {
		DistributionCenter dc = new DistributionCenter(1, "nellore", "international");
		Mockito.when(dcRespository.save(dc)).thenReturn(dc);
		assertThat(dcService.createDc(dc)).isEqualTo(dc);
	}

	@Test
	public void testGetAllDcs() throws Exception {
		DistributionCenter dc1 = new DistributionCenter(1, "nellore", "international");
		DistributionCenter dc2 = new DistributionCenter(2, "Gudur", "regional");
		List<DistributionCenter> dc = new ArrayList<>();
		dc.add(dc1);
		dc.add(dc2);
		Mockito.when(dcRespository.findAll()).thenReturn(dc);
		assertThat(dcService.getAllDcs()).isEqualTo(dc);
	}
	@Test
	public void testGetDcById() throws DcNotFoundException {
		when(dcRespository.findById(1L)).thenReturn(Optional.of(new DistributionCenter(1, "nellore", "international")));

		ResponseEntity<DistributionCenter> dc = dcService.getDcById(1);

	}
	@Test
	public void testDeleteDc() throws DcNotFoundException {
		DistributionCenter dc1 = new DistributionCenter(3, "nellore", "international");
		Mockito.doNothing().when(dcService).deleteDc(Mockito.any(Integer.class));
		dcService.deleteDc(11);
		verify(dcService, times(1)).deleteDc(11);
	}
	@Test
	public void testUpdateDc() throws Exception{
		DistributionCenter dc1 = new DistributionCenter(3, "nellore", "international");
		when(dcRespository.save(dc1)).thenReturn(dc1);
		//DistributionCenter dc=dcService.updateDc(3,dc1);
		assertThat(dcService.updateDc(3,dc1)).isEqualTo(dc1);
	}



	/*@Test
	public void testDeleteDcs() throws Exception{
		DistributionCenter dc = new DistributionCenter(1010, "Pune", "Regional");


	}*/
	/*@Test
	public void testSearchDc() throws DcNotFoundException {​​​​​​
		final long id = 1010L;
		DistributionCenter dc = new DistributionCenter(1010, "Pune", "Regional");
		final ResponseEntity<DistributionCenter> expected = dcService.getDcById(id);
		assertThat(expected).isNotNull();
	}​​​​​​​*/
	/*@Test
	void testDeleteDc() throws DcNotFoundException {
		DistributionCenter dc1 = new DistributionCenter(1, "nellore", "international");
		final long id=1;
		Mockito.doNothing().when(dcRespository).delete(dc1);
		dcRespository.delete(dc1);
		verify(dcRespository,times(1)).deleteById(id);

	}*/
	/*@Test
	void testSearchDc() throws DcNotFoundException {
		final long id = 1010;
		DistributionCenter dc = new DistributionCenter(1010, "gudur", "Regional");
		//given(dcRepo.findById(id)).willReturn(Optional.of(dc));
		Mockito.when(dcRespository.findById(id)).thenReturn(Optional.of(dc));
		*//*final ResponseEntity<DistributionCenter> expected=dcService.getDcById(id);
		assertThat(expected).isNotNull();*//*
		assertThat(dcService.getDcById(1010)).isEqualTo(dc);
	}*/

	/*@Test
	public void testDeleteDc() throws Exception {
		DistributionCenter dc = new DistributionCenter(1, "nellore", "international");
		Mockito.when(dcRespository.findById(1L)).thenReturn(Optional.of(dc));
		Mockito.when(dcRespository.existsById(dc.getDcNumber())).thenReturn(false);
		assertFalse(dcRespository.existsById(dc.getDcNumber()));
	}*/

	/*@Test
	void testDeleteDc() throws DcNotFoundException {
		final long id = 123L;
		dcService.deleteDc(id);
		verify(dcRespository, times(1)).deleteById(id);

	}*/

	/*
    private long dcNumber=1234L;
	private String dcType="regional";
	private String dcCity="bangalore";*/
//@Rollback(false)
/*@Test
	public void testCreateDc() {
		DistributionCenter dc= new DistributionCenter(1001L,"hydarabad","regional");
		DistributionCenter savedDc=dcRespository.save(dc);
		assertNotNull(savedDc);
	}*/
	/*@Test
	public void testCreateDc() {
		testEntityManager.persist(new DistributionCenter(1001L,"hyd","regional"));

		DistributionCenter dc = dcRespository.findById(1001L);

		assertThat(dc.getDcNumber()).isEqualTo(1001L);
	}*/
	/*@Test
   public void TestCreateDc() {

	   DistributionCenter dc = dcRespository.save(new DistributionCenter(1001L,"hyd","regional"));

	   assertThat(dc.getDcNumber()).isGreaterThan(0);
   }*/
   /* @Test
    public void testFindDcById() {

        DistributionCenter distributionCenter = new DistributionCenter(1001L, "hydarabad", "regional");
        when(dcRespository.findById(1001L)).thenReturn(Optional.of(distributionCenter));
        assertNotNull();
    }*/
  /* @Test
   public void delete() throws DcNotFoundException {
       DistributionCenter dc = DistributionCenter.createModelObject(dcNumber, dcType, dcCity);
       when(dcRespository.findById(dcNumber)).thenReturn(Optional.of(dc));

       Person returned = personService.delete(PERSON_ID);

       verify(personRepositoryMock, times(1)).findOne(PERSON_ID);
       verify(personRepositoryMock, times(1)).delete(deleted);
       verifyNoMoreInteractions(personRepositoryMock);

       assertEquals(deleted, returned);
   }
*/
/*@Test
	public void testFindDcById(){
		long dcNumber=1001L;
		String dcCity="bangalore";
		String dcType="regional";
	DistributionCenter dcc= dcRespository.findById(dcNumber);
		assertThat(dcc.getDcNumber()).isEqualTo(dcNumber);

	}

	@Test
	public void testUpdateDc(){

	}
	@Test
	public void testFindDcByIdError(){
		long dcNumber=1002L;
		String dcCity="bangalore";
		String dcType="regional";
		DistributionCenter dc=new DistributionCenter();
		Optional<DistributionCenter> dcc= dcRespository.findById(dcNumber);
		assertNull(dcc);


	}*/
}