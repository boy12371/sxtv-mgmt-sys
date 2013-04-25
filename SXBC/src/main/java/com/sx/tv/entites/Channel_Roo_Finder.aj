// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.Channel;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Channel_Roo_Finder {
    
    public static TypedQuery<Channel> Channel.findChannelsByType(int type) {
        EntityManager em = Channel.entityManager();
        TypedQuery<Channel> q = em.createQuery("SELECT o FROM Channel AS o WHERE o.type = :type", Channel.class);
        q.setParameter("type", type);
        return q;
    }
    
}
