// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.PlayData;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect PlayData_Roo_Jpa_Entity {
    
    declare @type: PlayData: @Entity;
    
    declare @type: PlayData: @Table(name = "playData");
    
    @Version
    @Column(name = "version")
    private Integer PlayData.version;
    
    public Integer PlayData.getVersion() {
        return this.version;
    }
    
    public void PlayData.setVersion(Integer version) {
        this.version = version;
    }
    
}