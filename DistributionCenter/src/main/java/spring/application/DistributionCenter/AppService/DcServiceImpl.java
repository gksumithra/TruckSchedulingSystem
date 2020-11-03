package spring.application.DistributionCenter.AppService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import spring.application.DistributionCenter.AppModel.DistributionCenter;
import spring.application.DistributionCenter.AppRepository.DcRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import spring.application.DistributionCenter.exception.DcAlreadyExistsException;
import spring.application.DistributionCenter.exception.DcNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
@Slf4j
@Service
public class DcServiceImpl implements IdcService {
    String exceptionMsgForDcNotFound;
    String exceptionMessageForAlreadyExists;
@Autowired
DcRespository dcRespository;
    Logger logger = LoggerFactory.getLogger(DcServiceImpl.class);
    @Override
       @Cacheable(value="distributioncenter",key="distributioncenter.dcNumber")
        public DistributionCenter createDc(@RequestBody DistributionCenter dc) throws DcAlreadyExistsException

    {
        if(dcRespository.findById(dc.getDcNumber()).isPresent())
        {

            throw new DcAlreadyExistsException("Dc Details Already Exists for the dcNumber::" +dc.getDcNumber());
        }
        return dcRespository.save(dc);
    }

    @CachePut(value = "distributioncenter",key = "#distributioncenter.dcNumber")
    @Override
    public DistributionCenter updateDc(@PathVariable(value = "dcNumber") long dcNumber, @RequestBody DistributionCenter dcdetails)
            throws  DcNotFoundException {
        if(dcRespository.findById(dcNumber).isPresent()) {
            dcdetails.setDcNumber(dcdetails.getDcNumber());
            dcdetails.setDcCity(dcdetails.getDcCity());
            dcdetails.setDcType(dcdetails.getDcType());
            logger.info("updating Dc details...");
            final DistributionCenter updatedDc = dcRespository.save(dcdetails);
            return dcRespository.save(updatedDc);
        }
        logger.error("An ERROR Message:");
        throw new DcNotFoundException("Dc not found for this DcNumber :: " + dcNumber);

    }

    @Cacheable(value = "distributioncenter",key = "#dcNumber")
    @Override
    public ResponseEntity<DistributionCenter> getDcById(@PathVariable(value = "dcNumber") long dcNumber)
            throws DcNotFoundException {
        DistributionCenter dc = dcRespository.findById(dcNumber)
                .orElseThrow(() -> new DcNotFoundException("Dc not found for this id :: " + dcNumber));
        return ResponseEntity.ok().body(dc);
    }
    @CacheEvict(value = "distributioncenter",key = "#dcNumber")
    @Override
    public void deleteDc(@PathVariable(value = "dcNumber") long dcNumber)
            throws DcNotFoundException {
        if(dcRespository.findById(dcNumber).isPresent()){
            logger.info("Deleting Dc by DcNumber");
            dcRespository.deleteById(dcNumber);
        }else
        {
            throw new DcNotFoundException("Dc Details not found for this dcNumber::" +dcNumber);
        }

    }

    @Cacheable(value = "distributioncenter")
    @Override
    public List<DistributionCenter> getAllDcs()
    {
        logger.info("Getting all the Dc Details...");
        return (List<DistributionCenter>) dcRespository.findAll();
    }

}

