package com.sx.tv.view;

import java.util.List;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.Score;

public class ScoreVO {

	private long tvid;

	private Channel channel;

	private float finalScore;

	private List<Score> scores;
	
	private String auditor;
	

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public long getTvid() {
		return tvid;
	}

	public void setTvid(long tvid) {
		this.tvid = tvid;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public float getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	
}
