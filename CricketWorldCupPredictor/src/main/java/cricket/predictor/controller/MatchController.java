package cricket.predictor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cricket.predictor.CricketWorldCupPredictorApp;
import cricket.predictor.models.MatchDetails;
import cricket.predictor.repository.MatchDetailsRepository;

@Controller
public class MatchController {

	private static final String REDIRECT_MATCH_FORM = "redirect:/match-form";

	@Autowired
	private MatchDetailsRepository matchDetailsRepository;

	@PostMapping("/insert-match")
	public String insertOrUpdateMatchDetails(@RequestParam("matchNumber") int matchNumber,
			@RequestParam("team1Name") String team1Name, @RequestParam("team2Name") String team2Name,
			@RequestParam("matchDate") String matchDate, RedirectAttributes redirectAttributes) {

		try {

			if (team1Name.equals(team2Name)) {
				redirectAttributes.addFlashAttribute("error", "Teams must be different!");
				return REDIRECT_MATCH_FORM + "?error=TeamsMustBeDifferent";
			}

			MatchDetails matchDetails = new MatchDetails();
			MatchDetails existingMatch = this.matchDetailsRepository.findByMatchNumber(matchNumber);

			String message;

			if (existingMatch == null) {
				matchDetails.setMatchNumber(matchNumber);
				message = "Match details inserted successfully.";
			} else {
				matchDetails = existingMatch;
				message = "Match details updated successfully.";
			}

			matchDetails.setMatchDate(new SimpleDateFormat("yyyy-MM-dd").parse(matchDate));

			matchDetails.setTeam1(team1Name);
			matchDetails.setTeam2(team2Name);

			this.matchDetailsRepository.save(matchDetails);
			redirectAttributes.addFlashAttribute("message", message);

			return REDIRECT_MATCH_FORM;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error",
					e instanceof ParseException ? "Invalid date format!" : "Error occured!");
			return e instanceof ParseException ? REDIRECT_MATCH_FORM + "?error=InvalidDateFormat"
					: REDIRECT_MATCH_FORM + "?error=ErrorOccured";
		}
	}

	@GetMapping("/match-form")
	public String showMatchForm(Model model) {
		Collections.sort(CricketWorldCupPredictorApp.teams);
		model.addAttribute("teams", CricketWorldCupPredictorApp.teams);
		return "match-form";
	}

}
