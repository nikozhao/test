package demo.creeper.dao.repository;

import demo.creeper.dao.entry.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/28/18
 * @Email: nikoz@synnex.com
 */
public interface FundRepository extends JpaRepository<Fund, String> {
}
