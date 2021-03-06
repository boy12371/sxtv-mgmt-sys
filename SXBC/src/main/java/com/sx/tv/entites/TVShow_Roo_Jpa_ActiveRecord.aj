// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.TVShow;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TVShow_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TVShow.entityManager;
    
    public static final EntityManager TVShow.entityManager() {
        EntityManager em = new TVShow().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TVShow.countTVShows() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TVShow o", Long.class).getSingleResult();
    }
    
    public static List<TVShow> TVShow.findAllTVShows() {
        return entityManager().createQuery("SELECT o FROM TVShow o", TVShow.class).getResultList();
    }
    
    public static TVShow TVShow.findTVShow(Long id) {
        if (id == null) return null;
        return entityManager().find(TVShow.class, id);
    }
    
    public static List<TVShow> TVShow.findTVShowEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TVShow o", TVShow.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TVShow.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TVShow.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TVShow attached = TVShow.findTVShow(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TVShow.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TVShow.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TVShow TVShow.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TVShow merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
