package spring.application.DistributionCenter.AppService;

import spring.application.DistributionCenter.AppModel.DistributionCenter;
import org.springframework.http.ResponseEntity;
import spring.application.DistributionCenter.exception.DcAlreadyExistsException;
import spring.application.DistributionCenter.exception.DcNotFoundException;

import java.util.List;

public interface IdcService {
  public DistributionCenter createDc(DistributionCenter distributionCenter) throws DcAlreadyExistsException;
    public void deleteDc(long dcNumber) throws DcNotFoundException;
    public DistributionCenter updateDc(long dcNumber, DistributionCenter dcdetails) throws DcNotFoundException;
    public ResponseEntity<DistributionCenter> getDcById(long dcNumber) throws  DcNotFoundException;
    public List<DistributionCenter> getAllDcs();
}
