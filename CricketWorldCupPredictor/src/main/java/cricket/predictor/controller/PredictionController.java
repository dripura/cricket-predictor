package cricket.predictor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cricket.predictor.CricketWorldCupPredictorApp;
import cricket.predictor.models.MatchPrediction;
import cricket.predictor.repository.MatchDetailsRepository;
import cricket.predictor.repository.MatchPredictionRepository;
import cricket.predictor.repository.PlayersRepository;

@Controller
public class PredictionController {

	class MatchPredictionValues {
		String userName;
		String mots;
		String mnor;
		String mnow;

		@Override
		public String toString() {
			return "MatchPredictionValues [userName=" + userName + ", mots=" + mots + ", mnor=" + mnor + ", mnow="
					+ mnow + "]";
		}

	}

	@Autowired
	private MatchPredictionRepository matchPredictionRepository;

	@Autowired
	private PlayersRepository playersRepository;

	@Autowired
	private MatchDetailsRepository matchDetailsRepository;

	private void populateMatchPrediction(MatchPrediction matchPrediction, String paramName, String paramValue) {
		if (paramName.endsWith(".team")) {
			matchPrediction.setTeam(paramValue);
		} else if (paramName.endsWith(".motm")) {
			matchPrediction.setMotm(paramValue);
		}
	}

	private String saveMatchPredictionsAndReturnForm(Map<Integer, MatchPrediction> matchPredictionMap,
			MatchPredictionValues matchPredictionValues) {

		if (matchPredictionMap.isEmpty()) {
			return "redirect:/prediction-form";
		} else {
			boolean existsByUsername = this.matchPredictionRepository.existsByUserName(matchPredictionValues.userName);
			if (!existsByUsername) {
				for (Entry<Integer, MatchPrediction> entry : matchPredictionMap.entrySet()) {
					int matchNumber = entry.getKey();
					MatchPrediction matchPrediction = entry.getValue();
					matchPrediction.setUserName(matchPredictionValues.userName);
					matchPrediction.setMots(matchPredictionValues.mots);
					matchPrediction.setMnor(matchPredictionValues.mnor);
					matchPrediction.setMnow(matchPredictionValues.mnow);
					matchPredictionMap.put(matchNumber, matchPrediction);
				}
				this.matchPredictionRepository.saveAll(matchPredictionMap.values());
				matchPredictionMap.clear();
				return "redirect:/success-form";
			} else {
				return "redirect:/failure-form";
			}

		}

	}

	private void setMatchPredicationValues(MatchPredictionValues matchPredictionValues, String paramName,
			String paramValue) {

		if (paramName.endsWith(".username")) {
			matchPredictionValues.userName = paramValue;
		} else if (paramName.endsWith(".mots")) {
			matchPredictionValues.mots = paramValue;
		} else if (paramName.endsWith(".mnor")) {
			matchPredictionValues.mnor = paramValue;
		} else if (paramName.endsWith(".mnow")) {
			matchPredictionValues.mnow = paramValue;
		}
	}

	@GetMapping("/prediction-form")
	public String showPredictionForm(Model model) {

		Map<String, List<String>> playersMap = new HashMap<>();
		for (String team : CricketWorldCupPredictorApp.teams) {
			playersMap.put(team, this.playersRepository.findNameByTeamOrderByNameAsc(team));
		}
		model.addAttribute("playersMap", playersMap);		
		model.addAttribute("allPlayersList", this.playersRepository.findNameByOrderByNameAsc());
		model.addAttribute("allBowlersList", this.playersRepository.findNameByRoleOrderByNameAsc("Bowler"));
		model.addAttribute("allBatsmanList", this.playersRepository.findNameByRoleOrderByNameAsc("Batsman"));
		model.addAttribute("matchDetailsList", this.matchDetailsRepository.findAllByOrderByMatchNumberAsc());

		return "prediction-form";
	}

	@PostMapping("/submit")
	public String submitPredictions(@RequestParam Map<String, String> matchPredictionsForm) {

		Map<Integer, MatchPrediction> matchPredictionMap = new HashMap<>();
		MatchPredictionValues matchPredictionValues = new MatchPredictionValues();

		for (Map.Entry<String, String> entry : matchPredictionsForm.entrySet()) {
			String paramName = entry.getKey();
			String paramValue = entry.getValue();

			if (paramName.matches("matchPredictions\\[\\d+\\].*")) {
				int matchNumber = Integer.parseInt(paramName.replaceAll("\\D+", ""));

				if (matchNumber == 0) {
					setMatchPredicationValues(matchPredictionValues, paramName, paramValue);
				} else {
					MatchPrediction matchPrediction = matchPredictionMap.computeIfAbsent(matchNumber, k -> {
						MatchPrediction newMatchPrediction = new MatchPrediction();
						newMatchPrediction.setMatchNumber(matchNumber);
						return newMatchPrediction;
					});

					populateMatchPrediction(matchPrediction, paramName, paramValue);
					matchPredictionMap.put(matchNumber, matchPrediction);

				}
			}
		}

		return saveMatchPredictionsAndReturnForm(matchPredictionMap, matchPredictionValues);
	}

}
