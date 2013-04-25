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
@RooJpaActiveRecord(table = "deptComments", finders = { "findDeptCommentsesByTvshow" })
public class DeptComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "isRecommend")
    private Boolean isRecommend;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private RecommendClass recommendLevel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Channel recommendChannel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private TVShow tvshow;

    @Column
    private String comments;
}
