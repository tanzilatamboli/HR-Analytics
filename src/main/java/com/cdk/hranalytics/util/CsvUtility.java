package com.cdk.hranalytics.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CsvUtility {
    public static List<String> readLines(String filePath){
        List<String> detailsList = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            detailsList = new ArrayList();
            String line = br.readLine();
            while (line != null) {
                detailsList.add(line);
            }
        } catch (FileNotFoundException e) {
            //throw new EcomException(e.toString());
        } catch (IOException e) {
            //throw new EcomException(e.toString());
        }finally {
            close(br);
        }
        return detailsList;
    }
    private static void close(Object... objects) {

        for (Object object : objects) {
            if (null != object) {
                try {
                    if (object instanceof Reader) {
                        ((Reader) object).close();
                    } else if (object instanceof Writer) {
                        ((Writer) object).close();
                    } else {
                        //throw new Exception("Invalid Argument !!!");
                    }
                } catch (IOException e) {
                    //throw new EcomException(e.toString());
                }
            }
        }
    }
}
