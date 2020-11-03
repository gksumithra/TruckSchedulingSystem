package SpringApplication.DcSlots.AppRepository;
import SpringApplication.DcSlots.AppModel.DcSlots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcSlotRepository extends CrudRepository<DcSlots,Long> {
}
