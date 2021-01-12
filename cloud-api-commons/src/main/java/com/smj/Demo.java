package com.smj;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.smj.entities.TestStudent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<TestStudent> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            String s = ""+i;
            for (int j = 1; j < 10000; j++){
                s = s+"啊";
            }

            int r = i%3;
            if (r == 0){
                TestStudent testStudent = new TestStudent();
                testStudent.setClassName("零班");
                testStudent.setName(s);
                testStudent.setNum("学号"+i);
                tList.add(testStudent);
            }else if (r == 1){
                TestStudent testStudent = new TestStudent();
                testStudent.setClassName("一班");
                testStudent.setName(s);
                testStudent.setNum("学号"+i);
                tList.add(testStudent);
            }else {
                TestStudent testStudent = new TestStudent();
                testStudent.setClassName("二班");
                testStudent.setName(s);
                testStudent.setNum("学号"+i);
                tList.add(testStudent);
            }
        }
        Map<String, List<TestStudent>> listMap = tList.stream().collect(Collectors.groupingBy(TestStudent::getClassName));


        try{
            List<Map<String,Object>> sheets = new ArrayList<Map<String,Object>>();

            listMap.forEach((k, v) ->{
                Map<String,Object> sheet = new HashMap<String,Object>();
                List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();//构造对象等同于@Excel
                entity.add(new ExcelExportEntity("学号", "num"));
                entity.add(new ExcelExportEntity("姓名", "name"));
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                v.forEach(va ->{
                    Map<String,Object> h1 = new HashMap<String,Object>();
                    h1.put("num",va.getNum());
                    h1.put("name",va.getName());
                    list.add(h1);
                });
                sheet.put(NormalExcelConstants.CLASS, ExcelExportEntity.class);
                sheet.put(NormalExcelConstants.DATA_LIST, list);
                sheet.put(NormalExcelConstants.PARAMS, new ExportParams(null, k));
                sheet.put(NormalExcelConstants.MAP_LIST, entity);
                sheets.add(sheet);
            });

            /*for(int i = 0;i<10;i++) {
                Map<String,Object> sheet = new HashMap<String,Object>();

                List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();//构造对象等同于@Excel

                entity.add(new ExcelExportEntity("性别", "sex"));
                entity.add(new ExcelExportEntity("姓名", "name"));

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Map<String,Object> h1 = new HashMap<String,Object>();
                h1.put("name", "name" + i);
                h1.put("sex", "sex" +  i);
                Map<String,Object> h2 = new HashMap<String,Object>();
                h2.put("name", "name" +i+i);
                h2.put("sex", "sex" +i+i);
                list.add(h1);
                list.add(h2);

                sheet.put(NormalExcelConstants.CLASS, ExcelExportEntity.class);
                sheet.put(NormalExcelConstants.DATA_LIST, list);
                sheet.put(NormalExcelConstants.PARAMS, new ExportParams(null, "sheet"+i));
                sheet.put(NormalExcelConstants.MAP_LIST, entity);
                sheets.add(sheet);
            }*/
            Workbook workbook = new HSSFWorkbook();

            for(Map<String,Object> map : sheets) {
                ExcelExportService server = new ExcelExportService();
                ExportParams param = (ExportParams) map.get("params");
                @SuppressWarnings("unchecked")
                List<ExcelExportEntity> entity = (List<ExcelExportEntity>) map.get("mapList");
                Collection<?> data = (Collection<?>) map.get("data");
                server.createSheetForMap(workbook, param, entity, data);
            }
            FileOutputStream fos = new FileOutputStream("D:/ExcelExportForMap.tt.xls");
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
