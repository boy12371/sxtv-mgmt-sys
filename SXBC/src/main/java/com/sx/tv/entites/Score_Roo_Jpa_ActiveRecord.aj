// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Score;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Score_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Score.entityManager;
    
    public static final EntityManager Score.entityManager() {
        EntityManager em = new Score().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Score.countScores() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Score o", Long.class).getSingleResult();
    }
    
    public static List<Score> Score.findAllScores() {
        return entityManager().createQuery("SELECT o FROM Score o", Score.class).getResultList();
    }
    
    public static Score Score.findScore(Integer id) {
        if (id == null) return null;
        return entityManager().find(Score.class, id);
    }
    
    public static List<Score> Score.findScoreEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Score o", Score.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Score.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Score.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Score attached = Score.findScore(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Score.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Score.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Score Score.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Score merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}