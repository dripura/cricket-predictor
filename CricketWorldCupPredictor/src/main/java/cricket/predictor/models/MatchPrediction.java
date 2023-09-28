package cricket.predictor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MatchPrediction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String mots;

	private String userName;

	private String mnow;

	private String mnor;

	private int matchNumber;

	private String team;

	private String motm;

	public int getId() {
		return id;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public String getMnor() {
		return mnor;
	}

	public String getMnow() {
		return mnow;
	}

	public String getMotm() {
		return motm;
	}

	public String getMots() {
		return mots;
	}

	public String getTeam() {
		return team;
	}

	public String getUserName() {
		return userName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

	public void setMnor(String mnor) {
		this.mnor = mnor;
	}

	public void setMnow(String mnow) {
		this.mnow = mnow;
	}

	public void setMotm(String motm) {
		this.motm = motm;
	}

	public void setMots(String mots) {
		this.mots = mots;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "MatchPrediction [id=" + id + ", mots=" + mots + ", userName=" + userName + ", mnow=" + mnow + ", mnor="
				+ mnor + ", matchNumber=" + matchNumber + ", team=" + team + ", motm=" + motm + "]";
	}
	
	

}
