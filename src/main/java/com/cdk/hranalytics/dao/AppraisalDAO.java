package com.cdk.hranalytics.dao;

import com.cdk.hranalytics.domain.Appraisal;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AppraisalDAO {
        private HibernateTemplate hibernateTemplate;


    public AppraisalDAO() {
        System.out.println("heelllooo");
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Appraisal saveOrUpdate(Appraisal appraisal){
        Appraisal appraisal1 = new Appraisal();
        appraisal1.setId(appraisal.getId());
        appraisal1.setRatingPeriod(appraisal.getRatingPeriod());
        appraisal1.setRating(appraisal.getRating());
        appraisal1.setSalary(appraisal.getSalary());
        appraisal1.setPromotedTo(appraisal.getPromotedTo());
        hibernateTemplate.saveOrUpdate(appraisal1);
        return appraisal;
    }

    public List<Appraisal> getAppraisalById(int id){
        return (List<Appraisal>)hibernateTemplate.findByNamedParam("from com.cdk.hranalytics.domain.Appraisal a where  a.id=:aid", "aid", id);
    }
}
