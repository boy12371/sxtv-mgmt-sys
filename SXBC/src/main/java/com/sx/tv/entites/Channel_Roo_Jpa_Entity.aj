// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Channel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Channel_Roo_Jpa_Entity {
    
    declare @type: Channel: @Entity;
    
    declare @type: Channel: @Table(name = "channel");
    
    @Version
    @Column(name = "version")
    private Integer Channel.version;
    
    public Integer Channel.getVersion() {
        return this.version;
    }
    
    public void Channel.setVersion(Integer version) {
        this.version = version;
    }
    
}