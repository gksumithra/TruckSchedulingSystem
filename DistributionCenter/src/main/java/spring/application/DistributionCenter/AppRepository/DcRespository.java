package spring.application.DistributionCenter.AppRepository;

import spring.application.DistributionCenter.AppModel.DistributionCenter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcRespository extends CrudRepository<DistributionCenter, Long> {
}
