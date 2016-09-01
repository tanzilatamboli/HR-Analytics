package com.cdk.hranalytics.controller;

import com.cdk.hranalytics.dao.AppraisalDAO;
import com.cdk.hranalytics.domain.Appraisal;
import com.cdk.hranalytics.service.DataUtility;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Created by dullus on 8/31/2016.
 */
@Controller
public class AppraisalController {
/*
    @Autowired
    private DataUtility dataUtility = null;

    public DataUtility getDataUtility() {
        return dataUtility;
    }

    public void setDataUtility(DataUtility dataUtility) {
        this.dataUtility = dataUtility;
    }
*/
    private String filePath;

    @RequestMapping(value = "/read",method = RequestMethod.GET)
    public
    @ResponseBody
    String read(HttpServletRequest request, HttpServletResponse response){
        System.out.println("In read method");

       /* DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> fileItemList = sf.parseRequest(request);
            FileItem fileItem = fileItemList.get(0);
            filePath = "C:\\Users\\dullus\\Pictures\\Slide Shows\\" + fileItem.getName();
            File file = new File(filePath);
            fileItem.write(file);
            DataUtility.read(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return "success";
    }

    @RequestMapping(value = "/getAppraisal",method = RequestMethod.GET)
    public
    @ResponseBody
    List<Appraisal> getAppraisal(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("eid"));
        AppraisalDAO appraisalDAO = new AppraisalDAO();
        return appraisalDAO.getAppraisalById(id);
    }
}
