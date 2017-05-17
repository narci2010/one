package com.lcw.one.workflow.dao;

import com.lcw.one.workflow.entity.FlowAuditItemEO;
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class FlowAuditItemEODao extends BaseRepositoryImpl<FlowAuditItemEO, String> {

    @Autowired
    public FlowAuditItemEODao(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(FlowAuditItemEO.class, entityManager), entityManager);
    }

}