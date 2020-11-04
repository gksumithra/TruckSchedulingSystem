package spring.application.DistributionCenter.AppController;

import org.springframework.http.HttpStatus;
import spring.application.DistributionCenter.AppModel.DistributionCenter;
import spring.application.DistributionCenter.AppModelDto.DcDto;
import spring.application.DistributionCenter.AppService.IdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.application.DistributionCenter.exception.DcAlreadyExistsException;
import spring.application.DistributionCenter.exception.DcNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/root/v1/dc")
public class DcController {
    @Autowired
    private IdcService idcService;


      @PostMapping
    public ResponseEntity<DistributionCenter> createDc (@RequestBody DcDto dcDto) throws DcAlreadyExistsException {
          DistributionCenter dc = new DistributionCenter();
          dc.setDcNumber(dcDto.getDcNumber());
          dc.setDcType(dcDto.getDcType());
          dc.setDcCity(dcDto.getDcCity());
            idcService.createDc(dc);
        return ResponseEntity.status(HttpStatus.CREATED).body(dc);
    }

    @PutMapping("/{dcNumber}")
    public ResponseEntity<DistributionCenter> updateDc(@PathVariable(value = "dcNumber") long dcNumber, @RequestBody DcDto dcDto) throws DcNotFoundException {
        DistributionCenter dc = new DistributionCenter();
        dc.setDcNumber(dcDto.getDcNumber());
        dc.setDcType(dcDto.getDcType());
        dc.setDcCity(dcDto.getDcCity());
          return ResponseEntity.ok(idcService.updateDc(dcNumber,dc));
    }
    @GetMapping("/{dcNumber}")
    public ResponseEntity<DistributionCenter> getDcDetailsById(@PathVariable(value="dcNumber") long dcNumber) throws  DcNotFoundException {
        ResponseEntity<DistributionCenter> dcs=  idcService.getDcById(dcNumber);
        return dcs;
    }

    @DeleteMapping("/{dcNumber}")
    public ResponseEntity<DistributionCenter> deleteDc(@PathVariable("dcNumber") long dcNumber) throws DcNotFoundException {
        idcService.deleteDc(dcNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<DistributionCenter>> getAllDcs(){
        return ResponseEntity.ok(idcService.getAllDcs());
    }


}
