package SpringApplication.DcSlots.AppService;

import SpringApplication.DcSlots.AppModel.DcSlots;
import SpringApplication.DcSlots.exception.DcSlotAlreadyExistsException;
import SpringApplication.DcSlots.exception.DcSlotNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public interface IdcSlotService {
    public DcSlots createDcSlots(@RequestBody DcSlots dcSlots) throws DcSlotAlreadyExistsException;

    ResponseEntity<DcSlots> getDcSlotById(Long dcNumber) throws DcSlotNotFoundException ;

    DcSlots updateDcSlot(Long dcNumber, DcSlots dcSlotDetails) throws DcSlotNotFoundException;

    void deleteDcSlot(long dcNumber) throws DcSlotNotFoundException;

    List<DcSlots> getAllDcSlots();
}
