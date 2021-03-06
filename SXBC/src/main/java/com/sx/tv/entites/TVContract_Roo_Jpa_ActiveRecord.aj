// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.TVContract;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TVContract_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TVContract.entityManager;
    
    public static final EntityManager TVContract.entityManager() {
        EntityManager em = new TVContract().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TVContract.countTVContracts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TVContract o", Long.class).getSingleResult();
    }
    
    public static List<TVContract> TVContract.findAllTVContracts() {
        return entityManager().createQuery("SELECT o FROM TVContract o", TVContract.class).getResultList();
    }
    
    public static TVContract TVContract.findTVContract(Integer id) {
        if (id == null) return null;
        return entityManager().find(TVContract.class, id);
    }
    
    public static List<TVContract> TVContract.findTVContractEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TVContract o", TVContract.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TVContract.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TVContract.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TVContract attached = TVContract.findTVContract(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TVContract.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TVContract.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TVContract TVContract.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TVContract merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
