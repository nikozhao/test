package demo.creeper.dao.repository;

import demo.creeper.dao.entry.Fund;
import demo.creeper.dao.entry.FundMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/30/18
 * @Email:
 */
public interface FundMasterRepository extends JpaRepository<FundMaster, String> {
}
