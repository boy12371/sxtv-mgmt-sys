// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Company;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Company_Roo_Jpa_Entity {
    
    declare @type: Company: @Entity;
    
    declare @type: Company: @Table(name = "company");
    
    @Version
    @Column(name = "version")
    private Integer Company.version;
    
    public Integer Company.getVersion() {
        return this.version;
    }
    
    public void Company.setVersion(Integer version) {
        this.version = version;
    }
    
}
