// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.ScoreIndicator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect ScoreIndicator_Roo_Jpa_Entity {
    
    declare @type: ScoreIndicator: @Entity;
    
    declare @type: ScoreIndicator: @Table(name = "scoreIndicator");
    
    @Version
    @Column(name = "version")
    private Integer ScoreIndicator.version;
    
    public Integer ScoreIndicator.getVersion() {
        return this.version;
    }
    
    public void ScoreIndicator.setVersion(Integer version) {
        this.version = version;
    }
    
}