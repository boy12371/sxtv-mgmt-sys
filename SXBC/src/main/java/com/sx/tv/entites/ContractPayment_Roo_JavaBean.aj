// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.sx.tv.entites;

import com.sx.tv.entites.ContractPayment;
import com.sx.tv.entites.TVContract;
import java.util.Date;

privileged aspect ContractPayment_Roo_JavaBean {
    
    public Integer ContractPayment.getId() {
        return this.id;
    }
    
    public void ContractPayment.setId(Integer id) {
        this.id = id;
    }
    
    public Float ContractPayment.getAmount() {
        return this.amount;
    }
    
    public void ContractPayment.setAmount(Float amount) {
        this.amount = amount;
    }
    
    public int ContractPayment.getInvoiceCount() {
        return this.invoiceCount;
    }
    
    public void ContractPayment.setInvoiceCount(int invoiceCount) {
        this.invoiceCount = invoiceCount;
    }
    
    public Date ContractPayment.getPayDate() {
        return this.payDate;
    }
    
    public void ContractPayment.setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    
    public String ContractPayment.getPayBy() {
        return this.payBy;
    }
    
    public void ContractPayment.setPayBy(String payBy) {
        this.payBy = payBy;
    }
    
    public Date ContractPayment.getReciveDate() {
        return this.reciveDate;
    }
    
    public void ContractPayment.setReciveDate(Date reciveDate) {
        this.reciveDate = reciveDate;
    }
    
    public String ContractPayment.getReciveBy() {
        return this.reciveBy;
    }
    
    public void ContractPayment.setReciveBy(String reciveBy) {
        this.reciveBy = reciveBy;
    }
    
    public TVContract ContractPayment.getContract() {
        return this.contract;
    }
    
    public void ContractPayment.setContract(TVContract contract) {
        this.contract = contract;
    }
    
}