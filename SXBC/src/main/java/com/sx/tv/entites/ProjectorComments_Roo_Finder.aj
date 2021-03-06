// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.ProjectorComments;
import com.sx.tv.entites.TVShow;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ProjectorComments_Roo_Finder {
    
    public static TypedQuery<ProjectorComments> ProjectorComments.findProjectorCommentsesByTvshow(TVShow tvshow) {
        if (tvshow == null) throw new IllegalArgumentException("The tvshow argument is required");
        EntityManager em = ProjectorComments.entityManager();
        TypedQuery<ProjectorComments> q = em.createQuery("SELECT o FROM ProjectorComments AS o WHERE o.tvshow = :tvshow", ProjectorComments.class);
        q.setParameter("tvshow", tvshow);
        return q;
    }
    
}
