package SpringApplication.Vendor;

import SpringApplication.Vendor.AppModel.Vendor;
import SpringApplication.Vendor.AppRepository.VendorRepository;
import SpringApplication.Vendor.AppService.VendorServiceImpl;
import SpringApplication.Vendor.Exception.VendorDetailsAlreadyExistsException;
import SpringApplication.Vendor.Exception.VendorDetailsNotFoundException;
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
import static org.bouncycastle.asn1.x500.style.RFC4519Style.dc;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VendorServiceImpl.class)
public class VendorServiceTest {
    @Autowired
    VendorServiceImpl vendorService;
    @MockBean
    private VendorRepository vendorRepository;

    @Test
     void testCreateVendor() throws VendorDetailsAlreadyExistsException {
        Vendor vendor = new Vendor(1,"sumithra","sumithra.gk@gmail.com",8096789234L,"bangalore");
        Mockito.when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.createVendor(vendor)).isEqualTo(vendor);
    }
    @Test
     void testGetAllVendors() {
        Vendor vendor1 = new Vendor(1,"sumithra","sumithra.gk@gmail.com",8096789234L,"bangalore");
        Vendor vendor2 = new Vendor(2,"anitha","anitha.gk@gmail.com",8096789212L,"chennai");
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor1);
        vendors.add(vendor2);
        Mockito.when(vendorRepository.findAll()).thenReturn(vendors);
        AssertionsForInterfaceTypes.assertThat(vendorService.getAllVendors()).isEqualTo(vendors);
    }
    @Test
     void testGetVendorById() throws VendorDetailsNotFoundException {
        when(vendorRepository.findById("sumitha.gk@gmail.com")).thenReturn(Optional.of(new Vendor(1,"sumithra","sumithra.gk@gmail.com",8096789212L,"chennai")));

        ResponseEntity<Vendor> truckResponseEntity = vendorService.getVendorByEmail("sumithra.gk@gmail.com");
        verify(vendorService, times(1)).getVendorByEmail("sumithra.gk@gmail.com");

    }
    @Test
    void testUpdateVendor() throws VendorDetailsNotFoundException {
        Vendor vendor =new Vendor(1,"sumithra","sumithra.gk@gmail.com",8096789212L,"chennai");
        Mockito.when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.updateVendor("sumithra.gk@gmail.com", vendor)).isEqualTo(dc);

    }

    @Test
    void testDeleteVendor() throws VendorDetailsNotFoundException {
        Vendor vendor =new Vendor(1,"sumithra","sumithra.gk@gmail.com",8096789212L,"chennai");
        Mockito.doNothing().when(vendorService).deleteVendor(Mockito.any(String.class));
        vendorService.deleteVendor("sumithra.gk@gmail.com");
        verify(vendorService, times(1)).deleteVendor("sumithra.gk@gmail.com");
    }
}
