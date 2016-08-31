package com.cdk.hranalytics.controller;

import com.cdk.hranalytics.service.DataDistributor;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dullus on 8/31/2016.
 */
@Controller
public class AppraisalController {
/*
    @Autowired
    private DataDistributor dataDistributor = null;

    public DataDistributor getDataDistributor() {
        return dataDistributor;
    }

    public void setDataDistributor(DataDistributor dataDistributor) {
        this.dataDistributor = dataDistributor;
    }
*/
    private String filePath;

    @RequestMapping(value = "/read",method = RequestMethod.GET)
    public
    @ResponseBody
    String read(HttpServletRequest request, HttpServletResponse response){
        System.out.println("In read method");

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(diskFileItemFactory);

        try {
            List<FileItem> fileItemList = sf.parseRequest(request);
            FileItem fileItem = fileItemList.get(0);
            filePath = "C:\\Users\\dullus\\Pictures\\Slide Shows\\" + fileItem.getName();
            File file = new File(filePath);
            fileItem.write(file);
            DataDistributor.read(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
