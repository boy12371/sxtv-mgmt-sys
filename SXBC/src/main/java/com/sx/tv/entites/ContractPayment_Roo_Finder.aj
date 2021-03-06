// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.ContractPayment;
import com.sx.tv.entites.TVContract;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ContractPayment_Roo_Finder {
    
    public static TypedQuery<ContractPayment> ContractPayment.findContractPaymentsByContract(TVContract contract) {
        if (contract == null) throw new IllegalArgumentException("The contract argument is required");
        EntityManager em = ContractPayment.entityManager();
        TypedQuery<ContractPayment> q = em.createQuery("SELECT o FROM ContractPayment AS o WHERE o.contract = :contract", ContractPayment.class);
        q.setParameter("contract", contract);
        return q;
    }
    
}
