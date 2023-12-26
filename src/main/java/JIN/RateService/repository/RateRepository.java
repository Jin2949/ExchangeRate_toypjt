package JIN.RateService.repository;

import JIN.RateService.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RateRepository extends JpaRepository<Rate, Long> {
}
