package com.cdk.hranalytics.controller;

import com.cdk.hranalytics.dao.AppraisalDAO;
import com.cdk.hranalytics.dao.AssociateDAO;
import com.cdk.hranalytics.domain.Appraisal;
import com.cdk.hranalytics.domain.Associate;
import com.cdk.hranalytics.util.CsvUtility;
import com.cdk.hranalytics.util.DateUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class DataDistributor {
    private static final String FIELD_SEPARATOR = ",";
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @RequestMapping(value = "/read.do", method = RequestMethod.POST)
    public
    @ResponseBody
    void read(String filePath)  {
        //String file="C:\\Users\\dullus\\Desktop\\HRProjectData.csv";
        List<Associate> associatesList = null;
        List<Appraisal> appraisalList = null;
        List<String> listOfRecords = CsvUtility.readLines(filePath);
        if (null != listOfRecords) {
            for (String line : listOfRecords) {
                associatesList.add(buildAssociate(line));
                appraisalList.add(buildAppraisal(line));
            }
        }
    }

    private Appraisal buildAppraisal(String line) {
        Appraisal appraisal = new Appraisal();
        String[] appraisalFields = line.split(FIELD_SEPARATOR);
        for(int i = 7 ; ; i=i+3){
            appraisal.setId(Integer.parseInt(appraisalFields[0]));
            String duration = appraisalFields[i].replaceFirst("Rating", "01");
            Date doj = DateUtility.stringToDate(duration,"DATE_FORMAT");
            java.sql.Date sqlDate = new java.sql.Date(doj.getTime());
            appraisal.setRatingPeriod(sqlDate);
            appraisal.setRating(Integer.parseInt(appraisalFields[i]));
            appraisal.setSalary(Double.parseDouble(appraisalFields[i+1]));
            appraisal.setPromotedTo(appraisalFields[i+2]);
            AppraisalDAO appraisalDAO = new AppraisalDAO();
            appraisalDAO.saveOrUpdate(appraisal);
            if (appraisalFields[i+1] == null){
                break;
            }
        }
        return appraisal;
    }


    private Associate buildAssociate(String line) {
        Associate associate = new Associate();
        String[] associateFields = line.split(FIELD_SEPARATOR);

        associate.setId(Integer.parseInt(associateFields[0]));
        associate.setFirstName(associateFields[1]);
        associate.setLastName(associateFields[2]);
        associate.setLocation(associateFields[3]);

        Date doj = DateUtility.stringToDate(associateFields[4],"DATE_FORMAT");
        java.sql.Date sqlDate = new java.sql.Date(doj.getTime());
        associate.setDateOfJoining(sqlDate);

        associate.setCurrentGrade(associateFields[5]);
        associate.setReportingManager(Integer.parseInt(associateFields[6]));

        AssociateDAO associateDAO = new AssociateDAO();
        associateDAO.saveOrUpdate(associate);
        return associate;
    }
}
