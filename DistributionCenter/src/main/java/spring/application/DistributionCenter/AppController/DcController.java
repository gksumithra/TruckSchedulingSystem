package spring.application.DistributionCenter.AppController;

import spring.application.DistributionCenter.AppModel.DistributionCenter;
import spring.application.DistributionCenter.AppService.IdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.application.DistributionCenter.exception.DcAlreadyExistsException;
import spring.application.DistributionCenter.exception.DcNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/root/v1/dc")
public class DcController {
    @Autowired
    private IdcService idcService;

    @PostMapping
    public ResponseEntity<?> createDc (@RequestBody DistributionCenter dc) throws DcAlreadyExistsException {
        DistributionCenter dcCenter= idcService.createDc(dc);
        return ResponseEntity.status(HttpStatus.CREATED).body(dcCenter);
    }
    @GetMapping("/{dcNumber}")
    public ResponseEntity<?> getDcDetailsById(@PathVariable(value="dcNumber") long dcNumber) throws  DcNotFoundException {
        ResponseEntity<DistributionCenter> dcs=  idcService.getDcById(dcNumber);
        return dcs;
    }

    @PutMapping("/{dcNumber}")
    public ResponseEntity<?> updateDc(@PathVariable(value = "dcNumber") long dcNumber,@RequestBody DistributionCenter dc) throws DcNotFoundException {
        return ResponseEntity.ok(idcService.updateDc(dcNumber,dc));
    }

    @DeleteMapping("/{dcNumber}")
    public ResponseEntity<?> deleteDc(@PathVariable("dcNumber") long dcNumber) throws DcNotFoundException {
        idcService.deleteDc(dcNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<DistributionCenter>> getAllDcs(){
        return ResponseEntity.ok(idcService.getAllDcs());
    }


}
