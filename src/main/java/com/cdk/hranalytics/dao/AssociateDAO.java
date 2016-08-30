package com.cdk.hranalytics.dao;

import com.cdk.hranalytics.domain.Associate;
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

    public Associate saveOrUpdate(Associate associate){
        Associate associate1 = new Associate();
        associate1.setId(associate.getId());
        associate1.setFirstName(associate.getFirstName());
        associate1.setLastName(associate.getLastName());
        associate1.setLocation(associate.getLocation());
        associate1.setDateOfJoining(associate.getDateOfJoining());
        associate1.setReportingManager(associate.getReportingManager());
        hibernateTemplate.saveOrUpdate(associate1);
        return associate;
    }




}
