package com.sx.tv.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "playData", finders = { "findPlayDatasByTvshow" })
public class PlayData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "playChannel")
    private String playChannel;

    @Column(name = "playName")
    private String playName;

    @Column(name = "playDate")
    private String playDate;

    @Column(name = "playRate")
    private String playRate;

    @Column(name = "playShare")
    private String playShare;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private TVShow tvshow;

    public PlayData() {
    }

    public PlayData(String playChannel, String playName, String playDate, String playRate, String playShare, TVShow tvshow) {
        this.playChannel = playChannel;
        this.playName = playName;
        this.playDate = playDate;
        this.playRate = playRate;
        this.playShare = playShare;
        this.tvshow = tvshow;
    }
}
