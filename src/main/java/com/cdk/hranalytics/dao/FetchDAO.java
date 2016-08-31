package com.cdk.hranalytics.dao;

import com.cdk.hranalytics.controller.AppraisalController;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by dullus on 8/31/2016.
 */
@Component
public class FetchDAO {

    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }





}
