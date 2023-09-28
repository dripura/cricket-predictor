package cricket.predictor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cricket.predictor.models.Players;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Integer> {

	@Query("SELECT name FROM Players ORDER BY name ASC")
	List<String> findNameByOrderByNameAsc();

	@Query("SELECT name FROM Players where team=:team ORDER BY name ASC")
	List<String> findNameByTeamOrderByNameAsc(String team);

	@Query("SELECT name FROM Players where role in (:role, 'All-rounder') ORDER BY name ASC")
	List<String> findNameByRoleOrderByNameAsc(String role);
	
}
