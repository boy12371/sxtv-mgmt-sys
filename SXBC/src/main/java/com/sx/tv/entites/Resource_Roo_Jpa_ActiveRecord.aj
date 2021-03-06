// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Resource;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Resource_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Resource.entityManager;
    
    public static final EntityManager Resource.entityManager() {
        EntityManager em = new Resource().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Resource.countResources() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Resource o", Long.class).getSingleResult();
    }
    
    public static List<Resource> Resource.findAllResources() {
        return entityManager().createQuery("SELECT o FROM Resource o", Resource.class).getResultList();
    }
    
    public static Resource Resource.findResource(Long id) {
        if (id == null) return null;
        return entityManager().find(Resource.class, id);
    }
    
    public static List<Resource> Resource.findResourceEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Resource o", Resource.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Resource.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Resource.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Resource attached = Resource.findResource(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Resource.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Resource.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Resource Resource.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Resource merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
