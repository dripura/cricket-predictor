package cricket.predictor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cricket.predictor.models.MatchDetails;

@Repository
public interface MatchDetailsRepository extends JpaRepository<MatchDetails, Integer> {
   
	List<MatchDetails> findAllByOrderByMatchNumberAsc();
    
	MatchDetails findByMatchNumber(int matchNumber);
}

