package com.sx.tv.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Score;
import com.sx.tv.entites.ScoreDetail;
import com.sx.tv.entites.ScoreIndicator;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.User;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/front/scores")
@Controller
public class ControllerScore {

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Score score, BindingResult bindingResult,
			Principal principal, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, score);
			return "scores/createScore";
		}
		uiModel.asMap().clear();
		User inputer = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		float avgScore = 0;
		for (ScoreDetail sd : score.getDetails()) {
			sd.persist();
			avgScore += sd.getScoreAmount() * sd.getIndicator().getProportion();
		}

		int _avg = (int) avgScore;
		List<RecommendClass> rList = RecommendClass.findAllRecommendClasses();
		for (RecommendClass rc : rList) {
			if (_avg >= rc.getStart() && _avg <= rc.getEnd()) {
				score.setRecommendLevel(rc);
				break;
			}
		}
		score.setAvgScore(_avg);
		score.setRatedBy(inputer);
		score.persist();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(score.getTvshow().getId()
						.toString(), httpServletRequest);
	}

	@RequestMapping(value = "/create/{tvid}", params = "toCreate", produces = "text/html")
	public String createForm(@PathVariable("tvid") Long tvid, Model uiModel,
			Principal principal) {
		User u = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		TVShow tv = TVShow.findTVShow(tvid);
		List<Score> scores = Score.findScoresByRatedByAndTvshow(u, tv)
				.getResultList();
		if (null == scores || scores.isEmpty()) {
			uiModel.addAttribute("channels", Channel.findAllChannels());
		} else {
			List<Channel> cns = Channel.findAllChannels();
			for (Score s : scores) {
				cns.remove(s.getRecommendChannel());
			}
			uiModel.addAttribute("channels", cns);
		}
		Score score = new Score();
		List<ScoreIndicator> siList = ScoreIndicator.findAllScoreIndicators();
		List<ScoreDetail> sd = new ArrayList<ScoreDetail>(siList.size());
		for (ScoreIndicator i : siList) {
			sd.add(new ScoreDetail(i, new Float(0)));
		}
		score.setTvshow(tv);
		score.setDetails(sd);
		uiModel.addAttribute("isCreate", true);
		uiModel.addAttribute("score", score);
		uiModel.addAttribute("recommendclasses",
				RecommendClass.findAllRecommendClasses());
		uiModel.addAttribute("users", User.findAllUsers());
		return "scores/createScore";
	}

	@RequestMapping(value = "/update/doUpdate", method = RequestMethod.PUT, produces = "text/html")
	public String doUpdate(@Valid Score score, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, score);
			return "scores/update";
		}
		uiModel.asMap().clear();
		float avgScore = 0;
		for (ScoreDetail sd : score.getDetails()) {
			avgScore += sd.getScoreAmount() * sd.getIndicator().getProportion();
			sd.persist();
		}
		int _avg = (int) avgScore;
		List<RecommendClass> rList = RecommendClass.findAllRecommendClasses();
		for (RecommendClass rc : rList) {
			if (_avg > rc.getStart() && _avg < rc.getEnd()) {
				score.setRecommendLevel(rc);
				break;
			}
		}
		score.setAvgScore(_avg);
		score.merge();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(score.getTvshow().getId()
						.toString(), httpServletRequest);
	}

	@RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
	public String toUpdateForm(@PathVariable("id") Integer id, Model uiModel) {
		uiModel.addAttribute("formURL", "/front/scores/update/doUpdate");
		populateEditForm(uiModel, Score.findScore(id));
		return "scores/update";
	}

	void populateEditForm(Model uiModel, Score score) {
		uiModel.addAttribute("score", score);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		uiModel.addAttribute("recommendclasses",
				RecommendClass.findAllRecommendClasses());
		// uiModel.addAttribute("scoredetails",
		// ScoreDetail.findAllScoreDetails());
		// uiModel.addAttribute("tvshows", TVShow.findAllTVShows());
		uiModel.addAttribute("users", User.findAllUsers());
	}

	
}
