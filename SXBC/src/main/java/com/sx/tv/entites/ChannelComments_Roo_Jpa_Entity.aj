// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.ChannelComments;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect ChannelComments_Roo_Jpa_Entity {
    
    declare @type: ChannelComments: @Entity;
    
    declare @type: ChannelComments: @Table(name = "channelComments");
    
    @Version
    @Column(name = "version")
    private Integer ChannelComments.version;
    
    public Integer ChannelComments.getVersion() {
        return this.version;
    }
    
    public void ChannelComments.setVersion(Integer version) {
        this.version = version;
    }
    
}
