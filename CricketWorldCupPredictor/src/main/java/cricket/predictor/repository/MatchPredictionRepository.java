package cricket.predictor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cricket.predictor.models.MatchPrediction;

@Repository
public interface MatchPredictionRepository extends JpaRepository<MatchPrediction, Integer> {
	
	boolean existsByUserName(String userName);
}

