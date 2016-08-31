package com.cdk.hranalytics.service;

import com.cdk.hranalytics.dao.AppraisalDAO;
import com.cdk.hranalytics.dao.AssociateDAO;
import com.cdk.hranalytics.domain.Appraisal;
import com.cdk.hranalytics.domain.Associate;
import com.cdk.hranalytics.util.CsvUtility;
import com.cdk.hranalytics.util.DateUtility;


import java.io.IOException;
import java.util.Date;
import java.util.List;


public class DataUtility {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Boolean read(String filePath)  {
        List<Associate> associatesList = null;
        List<Appraisal> appraisalList = null;
        List<String[]> listOfRecords = null;
        try {
            listOfRecords = CsvUtility.readLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != listOfRecords) {
            for (String[] line : listOfRecords) {
                associatesList.add(buildAssociate(line));
                appraisalList.add(buildAppraisal(line));
            }
        }
        return true;
    }

    private static Appraisal buildAppraisal(String[] line) {
        Appraisal appraisal = new Appraisal();

        for(int i = 7 ;i <= line.length ; i=i+3){
            appraisal.setId(Integer.parseInt(line[0]));
            String duration = line[i].replaceFirst("Rating", "01");
            Date doj = DateUtility.stringToDate(duration,"DATE_FORMAT");
            java.sql.Date sqlDate = new java.sql.Date(doj.getTime());
            appraisal.setRatingPeriod(sqlDate);
            appraisal.setRating(Integer.parseInt(line[i]));
            appraisal.setSalary(Double.parseDouble(line[i+1]));
            appraisal.setPromotedTo(line[i+2]);
            AppraisalDAO appraisalDAO = new AppraisalDAO();
            appraisalDAO.saveOrUpdate(appraisal);
        }
        return appraisal;
    }


    private static Associate buildAssociate(String line[]) {
        Associate associate = new Associate();
        associate.setId(Integer.parseInt(line[0]));
        associate.setFirstName(line[1]);
        associate.setLastName(line[2]);
        associate.setLocation(line[3]);

        Date doj = DateUtility.stringToDate(line[4],"DATE_FORMAT");
        java.sql.Date sqlDate = new java.sql.Date(doj.getTime());
        associate.setDateOfJoining(sqlDate);

        associate.setCurrentGrade(line[5]);
        associate.setReportingManager(Integer.parseInt(line[6]));

        AssociateDAO associateDAO = new AssociateDAO();
        associateDAO.saveOrUpdate(associate);
        return associate;
    }
}
