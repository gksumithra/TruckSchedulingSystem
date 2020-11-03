package SpringApplication.DcSlots.AppController;

import SpringApplication.DcSlots.AppModel.DcSlots;
import SpringApplication.DcSlots.AppService.IdcSlotService;
import SpringApplication.DcSlots.exception.DcSlotAlreadyExistsException;
import SpringApplication.DcSlots.exception.DcSlotNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping("/root/v1/dcslots")
@RestController
public class DcSlotsController {

@Autowired
IdcSlotService idcSlotService;
    @PostMapping
    public ResponseEntity<?> createDcSlots (@RequestBody DcSlots dcslots) throws DcSlotAlreadyExistsException {
        DcSlots dc= idcSlotService.createDcSlots(dcslots);
        return ResponseEntity.status(HttpStatus.CREATED).body(dc);
    }
    @GetMapping("/{dcNumber}")
    public ResponseEntity<DcSlots> getDcSlotDetailsById(@PathVariable(value="dcNumber") long dcNumber) throws DcSlotNotFoundException {
        ResponseEntity<DcSlots> dcslots=  idcSlotService.getDcSlotById(dcNumber);
        return dcslots;
    }


    @PutMapping("/{dcNumber}")
    public ResponseEntity<?> UpdateDcSlot(@PathVariable(value = "dcNumber") long dcNumber,@RequestBody DcSlots dcSlots) throws DcSlotNotFoundException{
        return ResponseEntity.ok(idcSlotService.updateDcSlot(dcNumber,dcSlots));
    }

    @DeleteMapping("/{dcNumber}")
    public ResponseEntity<?> deleteDcSlot(@PathVariable("dcNumber") long dcNumber) throws DcSlotNotFoundException {
        idcSlotService.deleteDcSlot(dcNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<DcSlots>> getAllDcsSlots(){
        return ResponseEntity.ok(idcSlotService.getAllDcSlots());
    }

}
