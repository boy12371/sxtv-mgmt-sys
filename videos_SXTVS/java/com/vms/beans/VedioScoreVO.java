package com.vms.beans;

import java.util.Date;

import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;

public class VedioScoreVO {
	private final static String AwardStr="推荐";
	private final static String UnAwardStr="不推荐";
	private final static String PurchaseStr="购买";
	private final static String UnPurchaseStr="不购买";
	
	private Integer id;
	private String vedioID;
	private String vedioName;
	private int status;
	private String examiner;
	private int userID;
	private int operator;
	private float storyScore;
	private float techScore;
	private float performScore;
	private float innovateScore;
	private float score;
	private float accuracy;
	private Date dateExamine;
	private String award;
	private String purchase;
	private int orientation;
	
	public VedioScoreVO(){}
	
	public VedioScoreVO(Vedioscore score){
		this.id = score.getId();
		this.vedioID = score.getVedio().getId();
		this.vedioName = score.getVedio().getVedioName();
		this.examiner = score.getExaminer().getEmployee().getName();
		this.userID = score.getExaminer().getId();
		this.storyScore = score.getStoryScore();
		this.techScore = score.getTechScore();
		this.performScore = score.getPerformScore();
		this.innovateScore = score.getInnovateScore();
		this.score = score.getScore();
		this.status = score.getVedio().getStatus().getId();
		this.setAccuracy(null==score.getAccuracy()?0:score.getAccuracy());
		this.dateExamine = score.getDateExamine();
		this.award = 1==score.getAward()? AwardStr : UnAwardStr;
		this.purchase = 1==score.getPurchase()? PurchaseStr : UnPurchaseStr;
		this.orientation = score.getOrientation();
		this.operator = score.getOperator().getId();
	}
	
	public Vedioscore toVedioscore(){
		Vedioscore score = new Vedioscore();
		score.setId(id);
		score.setStoryScore(storyScore);
		score.setTechScore(techScore);
		score.setPerformScore(performScore);
		score.setInnovateScore(innovateScore);
		score.setDateExamine(new Date());
		score.setOrientation(new Integer(orientation));
		if(null == award){
			score.setAward(new Integer(0));
		}else{
			score.setAward(new Integer(award));
		}
		if(null == purchase){
			score.setPurchase(new Integer(0));
		}else{
			score.setPurchase(new Integer(purchase));
		}
		Vediotape tape = new Vediotape();
		tape.setVedioName(vedioName);
		tape.setId(vedioID);
		score.setVedio(tape);
		
		User user = new User();
		user.setUserName(examiner);
		user.setId(userID);
		score.setExaminer(user);
		
		User operator = new User(new Integer(this.operator));
		score.setOperator(operator);
		return score;
	}
	
	public String getVedioName() {
		return vedioName;
	}
	public void setVedioName(String vedioName) {
		this.vedioName = vedioName;
	}
	public String getExaminer() {
		return examiner;
	}
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	public float getStoryScore() {
		return storyScore;
	}
	public void setStoryScore(float storyScore) {
		this.storyScore = storyScore;
	}
	public float getTechScore() {
		return techScore;
	}
	public void setTechScore(float techScore) {
		this.techScore = techScore;
	}
	public float getPerformScore() {
		return performScore;
	}
	public void setPerformScore(float performScore) {
		this.performScore = performScore;
	}
	public float getInnovateScore() {
		return innovateScore;
	}
	public void setInnovateScore(float innovateScore) {
		this.innovateScore = innovateScore;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}

	public void setDateExamine(Date dateExamine) {
		this.dateExamine = dateExamine;
	}

	public Date getDateExamine() {
		return dateExamine;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getAward() {
		return award;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setVedioID(String vedioID) {
		this.vedioID = vedioID;
	}

	public String getVedioID() {
		return vedioID;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getOperator() {
		return operator;
	}
	
}
