package com.sx.tv.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "score", finders = { "findScoresByRatedByAndTvshowidEquals", "findScoresByTvshowidEquals", "findScoresByRatedByAndTvshow", "findScoresByRatedBy", "findScoresByTvshow" })
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "avgScore")
    private Integer avgScore;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private TVShow tvshow;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Channel recommendChannel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User ratedBy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ScoreDetail> details;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private RecommendClass recommendLevel;
}
