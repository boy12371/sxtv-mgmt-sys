// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Score;
import com.sx.tv.entites.ScoreDetail;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.User;
import java.util.List;

privileged aspect Score_Roo_JavaBean {
    
    public Integer Score.getId() {
        return this.id;
    }
    
    public void Score.setId(Integer id) {
        this.id = id;
    }
    
    public Integer Score.getAvgScore() {
        return this.avgScore;
    }
    
    public void Score.setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
    }
    
    public TVShow Score.getTvshow() {
        return this.tvshow;
    }
    
    public void Score.setTvshow(TVShow tvshow) {
        this.tvshow = tvshow;
    }
    
    public Channel Score.getRecommendChannel() {
        return this.recommendChannel;
    }
    
    public void Score.setRecommendChannel(Channel recommendChannel) {
        this.recommendChannel = recommendChannel;
    }
    
    public User Score.getRatedBy() {
        return this.ratedBy;
    }
    
    public void Score.setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
    }
    
    public List<ScoreDetail> Score.getDetails() {
        return this.details;
    }
    
    public void Score.setDetails(List<ScoreDetail> details) {
        this.details = details;
    }
    
    public RecommendClass Score.getRecommendLevel() {
        return this.recommendLevel;
    }
    
    public void Score.setRecommendLevel(RecommendClass recommendLevel) {
        this.recommendLevel = recommendLevel;
    }
    
}
