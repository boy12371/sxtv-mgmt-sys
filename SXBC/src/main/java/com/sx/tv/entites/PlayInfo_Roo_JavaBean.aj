// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.TVShow;
import java.util.Date;

privileged aspect PlayInfo_Roo_JavaBean {
    
    public Integer PlayInfo.getId() {
        return this.id;
    }
    
    public void PlayInfo.setId(Integer id) {
        this.id = id;
    }
    
    public Integer PlayInfo.getRound() {
        return this.round;
    }
    
    public void PlayInfo.setRound(Integer round) {
        this.round = round;
    }
    
    public Float PlayInfo.getPrice() {
        return this.price;
    }
    
    public void PlayInfo.setPrice(Float price) {
        this.price = price;
    }
    
    public Date PlayInfo.getReservedFrom() {
        return this.reservedFrom;
    }
    
    public void PlayInfo.setReservedFrom(Date reservedFrom) {
        this.reservedFrom = reservedFrom;
    }
    
    public Date PlayInfo.getReservedTo() {
        return this.reservedTo;
    }
    
    public void PlayInfo.setReservedTo(Date reservedTo) {
        this.reservedTo = reservedTo;
    }
    
    public Date PlayInfo.getPlayDate() {
        return this.playDate;
    }
    
    public void PlayInfo.setPlayDate(Date playDate) {
        this.playDate = playDate;
    }
    
    public Channel PlayInfo.getPlayChannel() {
        return this.playChannel;
    }
    
    public void PlayInfo.setPlayChannel(Channel playChannel) {
        this.playChannel = playChannel;
    }
    
    public TVShow PlayInfo.getTvshow() {
        return this.tvshow;
    }
    
    public void PlayInfo.setTvshow(TVShow tvshow) {
        this.tvshow = tvshow;
    }
    
}
