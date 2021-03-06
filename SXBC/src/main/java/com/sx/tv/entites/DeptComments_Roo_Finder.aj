// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.TVShow;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect DeptComments_Roo_Finder {
    
    public static TypedQuery<DeptComments> DeptComments.findDeptCommentsesByTvshow(TVShow tvshow) {
        if (tvshow == null) throw new IllegalArgumentException("The tvshow argument is required");
        EntityManager em = DeptComments.entityManager();
        TypedQuery<DeptComments> q = em.createQuery("SELECT o FROM DeptComments AS o WHERE o.tvshow = :tvshow", DeptComments.class);
        q.setParameter("tvshow", tvshow);
        return q;
    }
    
}
