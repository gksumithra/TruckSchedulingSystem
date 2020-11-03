package spring.application.DistributionCenter.AppRepository;

import spring.application.DistributionCenter.AppModel.DistributionCenter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DcRespository extends CrudRepository<DistributionCenter, Long> {
    /*DistributionCenter findByName(long dcNumber);
    public Optional<DistributionCenter> findByDcName(String dcName);
*/
}
