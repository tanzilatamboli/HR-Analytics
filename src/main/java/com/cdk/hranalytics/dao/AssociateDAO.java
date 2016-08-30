package com.cdk.hranalytics.dao;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;


@Component
public class AssociateDAO {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }



}
