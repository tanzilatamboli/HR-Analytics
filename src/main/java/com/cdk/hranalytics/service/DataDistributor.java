package com.cdk.hranalytics.service;

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

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class DataDistributor {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @RequestMapping(value = "/read.do", method = RequestMethod.POST)
    public
    @ResponseBody
    void read(String filePath)  {
        //String file="C:\\Users\\dullus\\Desktop\\HRProjectData.csv";
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
    }

    private Appraisal buildAppraisal(String[] line) {
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


    private Associate buildAssociate(String line[]) {
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
