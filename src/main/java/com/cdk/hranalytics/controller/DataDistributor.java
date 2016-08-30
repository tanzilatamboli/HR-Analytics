package com.cdk.hranalytics.controller;

import com.cdk.hranalytics.domain.Appraisal;
import com.cdk.hranalytics.domain.Associate;
import com.cdk.hranalytics.util.CsvUtility;
import com.cdk.hranalytics.util.DateUtility;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tambolit on 8/30/2016.
 */
public class DataDistributor {
    private static final String FIELD_SEPARATOR = ",";
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static List<Associate> readRecords(String filePath)  {
        String file="C:\\Users\\tambolit\\Desktop\\HRProjectData.csv";
        List<Associate> associatesList = null;
        List<String> listOfRecords = CsvUtility.readLines(file);
        if (null != listOfRecords) {
            for (String line : listOfRecords) {
                associatesList.add(buildAssociate(line));
            }
        }
        return associatesList;
    }
    private static Associate buildAssociate(String record) {
        Associate associate = null;
        String[] associateFields = record.split(FIELD_SEPARATOR);
        associate = new Associate();
        associate.setId(Integer.parseInt(associateFields[0]));
        associate.setFirstName(associateFields[1]);
        associate.setLastName(associateFields[2]);
        associate.setLocation(associateFields[3]);
        Date doj = DateUtility.stringToDate(associateFields[4],"DATE_FORMAT");
        java.sql.Date sqlDate = new java.sql.Date(doj.getTime());
        associate.setDateOfJoining(sqlDate);
        associate.setCurrentGrade(associateFields[5]);
        associate.setReportingManager(Integer.parseInt(associateFields[6]));
        Date lastPromoDate = DateUtility.stringToDate(associateFields[7],"DATE_FORMAT");
        java.sql.Date promoSqlDate = new java.sql.Date(lastPromoDate.getTime());
        associate.setLastPromoDate(promoSqlDate);
        Set<Appraisal> appraisalsSet = new HashSet<>();

        return associate;
    }
}
