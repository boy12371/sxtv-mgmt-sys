package com.sx.tv.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooSerializable
@RooJpaActiveRecord(table = "projectorComments", finders = { "findProjectorCommentsesByTvshow" })
public class ProjectorComments {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "isRecommend")
    private Boolean isRecommend;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private RecommendClass projectorRecommend;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Channel recommendChannel;

    @Column(name = "comments")
    private String comments;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private TVShow tvshow;

    public RecommendClass getProjectorRecommend() {
        return projectorRecommend;
    }

    public void setProjectorRecommend(RecommendClass projectorRecommend) {
        this.projectorRecommend = projectorRecommend;
    }

    public Channel getRecommendChannel() {
        return recommendChannel;
    }

    public void setRecommendChannel(Channel recommendChannel) {
        this.recommendChannel = recommendChannel;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TVShow getTvshow() {
        return tvshow;
    }

    public void setTvshow(TVShow tvshow) {
        this.tvshow = tvshow;
    }
}
