package cricket.predictor.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MatchDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int matchNumber;
	
	private Date matchDate;
	
	private String result;
	
	private String team1;
	
	private String team2;

	public int getId() {
		return id;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public String getResult() {
		return result;
	}

	public String getTeam1() {
		return team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	
}
